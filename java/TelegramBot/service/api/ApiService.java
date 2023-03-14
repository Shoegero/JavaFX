package TelegramBot.service.api;

import TelegramBot.model.RootAnime;
import TelegramBot.model.RootCharacter;
import TelegramBot.model.RootManga;
import TelegramBot.model.RootUser;

import java.util.List;

public interface ApiService {
    //    String getToken();
    RootAnime searchAnime(String keyword);

    RootManga searchManga(String keyword);

    RootCharacter searchCharacter(String keyword);

    List<String> searchAnimeUrl();

    void changeRating(String rating, String id, String type, String userId);

    void sendDm(String message, String fromId, String toId);

    RootUser getId(String nickname);
}
