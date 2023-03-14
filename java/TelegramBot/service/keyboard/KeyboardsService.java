package TelegramBot.service.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface KeyboardsService {
    ReplyKeyboard startingKeyboard();

    ReplyKeyboard searchKeyboard();

    ReplyKeyboard gameKeyboard();
}
