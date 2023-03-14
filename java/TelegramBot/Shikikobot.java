package TelegramBot;

import TelegramBot.model.RootAnime;
import TelegramBot.model.RootCharacter;
import TelegramBot.model.RootManga;
import TelegramBot.model.RootUser;
import TelegramBot.service.api.ApiService;
import TelegramBot.service.api.ApiServiceImpl;
import TelegramBot.service.database.DatabaseService;
import TelegramBot.service.database.DatabaseServiceImpl;
import TelegramBot.service.keyboard.KeyboardsService;
import TelegramBot.service.keyboard.KeyboardsServiceImpl;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Shikikobot extends TelegramLongPollingBot {
    ApiService apiService = new ApiServiceImpl();
    DatabaseService databaseService = new DatabaseServiceImpl();
    KeyboardsService keyboardsService = new KeyboardsServiceImpl();
    List<String> gameList = new ArrayList<>(apiService.searchAnimeUrl());
    RootAnime rootAnime = new RootAnime();
    RootManga rootManga = new RootManga();
    RootCharacter rootCharacter = new RootCharacter();
    RootUser rootUser = new RootUser();
    BotState botState = new BotState(State.ROOT);

    @Override
    public String getBotUsername() {
        return "Shikikobot";
    }

    @Override
    public String getBotToken() {
        return "5851583723:AAGuopd0A48Y_oB3uIbs_sD9uuOLaY2L6Fc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMess = update.getMessage();
            String chatId = inMess.getChatId().toString();
            System.out.println(botState.getState().toString());
            parseMessage(inMess.getText(), chatId);
        }
    }

    public void sendMessage(String chatId, String response) throws TelegramApiException {
        SendMessage outMess = new SendMessage();
        outMess.setChatId(chatId);
        outMess.setText(response);
        execute(outMess);
    }

    public void sendMessageStarting(String chatId, String response) throws TelegramApiException {
        SendMessage outMess = new SendMessage();
        outMess.setChatId(chatId);
        outMess.setText(response);
        outMess.setReplyMarkup(keyboardsService.startingKeyboard());
        execute(outMess);
    }

    public void sendMessageSearching(String chatId, String response) throws TelegramApiException {
        SendMessage outMess = new SendMessage();
        outMess.setChatId(chatId);
        outMess.setText(response);
        outMess.setReplyMarkup(keyboardsService.searchKeyboard());
        execute(outMess);
    }

    public void sendMessageGame(String chatId, String response) throws TelegramApiException {
        SendMessage outMess = new SendMessage();
        outMess.setChatId(chatId);
        outMess.setText(response);
        outMess.setReplyMarkup(keyboardsService.gameKeyboard());
        execute(outMess);
    }

    @SneakyThrows
    public void parseMessage(String textMsg, String chatId) {
        String response;
        switch (botState.getState()) {
            case ROOT -> {
                switch (textMsg) {
                    case "/start" -> {
                        response = """
                                Йоу, какую команду хочешь?\s
                                /anime найдет всю инфу об интересующем тебя аниме
                                /manga найдет всю инфу об интересующей тебя манге
                                /character найдет всю инфу об интересующем тебя персонаже
                                /game запустит игру с выбором любимого аниме
                                /message отправит сообщение указанному пользователю на Shikimori""";
                        sendMessageStarting(chatId, response);
                    }
                    case "/anime" -> {
                        response = "Какое аниме интересует?";
                        botState.setState(State.SEARCH_ANIME);
                        sendMessage(chatId, response);
                    }
                    case "/manga" -> {
                        response = "Какая манга интересует?";
                        botState.setState(State.SEARCH_MANGA);
                        sendMessage(chatId, response);
                    }
                    case "/character" -> {
                        response = "Какой персонаж интересует?";
                        botState.setState(State.SEARCH_CHARACTER);
                        sendMessage(chatId, response);
                    }
                    case "/game" -> {
                        response = "Да начнется турнир";
                        sendMessage(chatId, response);
                        sendMessage(chatId, gameList.get(0));
                        sendMessageGame(chatId, gameList.get(1));
                        botState.setState(State.GAME);
                    }
                    case "/message" -> {
                        response = "Кому отправляем сообщение?";
                        botState.setState(State.MESSAGE_SEARCH_USER);
                        sendMessage(chatId, response);
                    }
                    default -> System.out.println("Такой команды не существует");
                }
            }
            case SEARCH_ANIME -> {
                rootAnime = apiService.searchAnime(textMsg);
                response = rootAnime.getRussian() + "\n"
                        + "Статус: " + rootAnime.getStatus() + "\n"
                        + "Рейтинг: " + rootAnime.getScore() + "\n"
                        + "Число эпизодов: " + rootAnime.getEpisodes() + "\n"
                        + "Дата выхода: " + rootAnime.getReleased_on() + "\n"
                        + "Ссылка: " + "shikimori.one" + rootAnime.getUrl() + "\n"
                        + "Для добавления аниме в БД введите /add, \nДля выставления оценки введите /rate, \nДля возврата к выбору стартовых команд /cancel";
                botState.setState(State.SEARCH_OPTION);
                sendMessageSearching(chatId, response);
            }
            case SEARCH_MANGA -> {
                rootManga = apiService.searchManga(textMsg);
                response = rootManga.getRussian() + "\n"
                        + "Статус: " + rootManga.getStatus() + "\n"
                        + "Рейтинг: " + rootManga.getScore() + "\n"
                        + "Дата выхода: " + rootManga.getReleased_on() + "\n"
                        + "Ссылка: " + "shikimori.one" + rootManga.getUrl() + "\n";
                botState.setState(State.ROOT);
                sendMessageStarting(chatId, response);
            }
            case SEARCH_CHARACTER -> {
                rootCharacter = apiService.searchCharacter(textMsg);
                response = rootCharacter.getRussian() + "\n"
                        + "Ссылка: " + "shikimori.one" + rootCharacter.getUrl() + "\n";
                botState.setState(State.ROOT);
                sendMessageStarting(chatId, response);
            }
            case SEARCH_OPTION -> {
                switch (textMsg) {
                    case "/add" -> {
                        response = "Вы уверены, что хотите добавить это аниме в базу данных? (/yes или /no)";
                        botState.setState(State.SEARCH_ADD);
                        sendMessage(chatId, response);
                    }
                    case "/rate" -> {
                        response = "Какую оценку вы хотите поставить этому аниме? (1-10, /cancel для отмены)";
                        botState.setState(State.SEARCH_RATE);
                        sendMessage(chatId, response);
                    }
                    case "/search" -> {
                        botState.setState(State.SEARCH_ANIME);
                        response = "Какое аниме интересует?";
                        sendMessage(chatId, response);
                    }
                    case "/cancel" -> {
                        botState.setState(State.ROOT);
                        response = "Возвращаемся";
                        sendMessageStarting(chatId, response);
                    }
                    default -> System.out.println("Такой команды не существует");
                }
            }
            case SEARCH_ADD -> {
                switch (textMsg) {
                    case "/yes" -> {
                        databaseService.add(rootAnime.getRussian()
                                , rootAnime.getScore()
                                , rootAnime.getStatus()
                                , rootAnime.getEpisodes()
                                , rootAnime.getReleased_on()
                                , "shikirmori.one" + rootAnime.getUrl());
                        botState.setState(State.SEARCH_OPTION);
                        response = "Аниме успешно добавлено";
                        sendMessageSearching(chatId, response);
                    }
                    case "/no" -> {
                        botState.setState(State.SEARCH_OPTION);
                        response = "Возвращаемся";
                        sendMessageSearching(chatId, response);
                    }
                    default -> System.out.println("Такой команды не существует");
                }
            }
            case SEARCH_RATE -> {
                if (textMsg.matches("\\b([1-9]|10)\\b")) {
                    response = "Оценка изменена на " + textMsg;
                    sendMessageStarting(chatId, response);
                    botState.setState(State.ROOT);
                    apiService.changeRating(textMsg, String.valueOf(rootAnime.getId()), "Anime", "102048");
                } else if (textMsg.equals("/cancel")) {
                    botState.setState(State.ROOT);
                    response = "Возвращаемся";
                    sendMessageSearching(chatId, response);
                } else {
                    response = "Укажите балл от 1 до 10 или введите /cancel для отмены";
                    sendMessage(chatId, response);
                    botState.setState(State.SEARCH_RATE);
                }

            }
            case GAME -> {
                switch (textMsg) {
                    case "/up" -> {
                        botState.setState(State.GAME);
                        gameList.remove(1);
                        if (gameList.size() == 1) {
                            sendMessageStarting(chatId, "Победитель турнира: " + gameList.get(0));
                            botState.setState(State.ROOT);
                        } else {
                            sendMessage(chatId, gameList.get(0));
                            sendMessageGame(chatId, gameList.get(1));
                        }
                    }
                    case "/down" -> {
                        botState.setState(State.GAME);
                        gameList.remove(0);
                        if (gameList.size() == 1) {
                            sendMessageStarting(chatId, "Победитель турнира: " + gameList.get(0));
                            botState.setState(State.ROOT);
                        } else {
                            sendMessage(chatId, gameList.get(0));
                            sendMessageGame(chatId, gameList.get(1));
                        }
                    }
                    default -> System.out.println("Выберете аниме через команды /up или /down");
                }
            }
            case MESSAGE_SEARCH_USER -> {
                rootUser = apiService.getId(textMsg);
                response = "Напишите что вы хотите отправить";
                sendMessage(chatId, response);
                botState.setState(State.MESSAGE_SEND);
            }
            case MESSAGE_SEND -> {
                response = "Сообщение успешно отправлено";
                sendMessageStarting(chatId, response);
                botState.setState(State.ROOT);
                apiService.sendDm(textMsg, "102048", String.valueOf(rootUser.getId()));
            }
            default -> System.out.println("Введите /start для начала беседы с ботом");
        }
    }
}