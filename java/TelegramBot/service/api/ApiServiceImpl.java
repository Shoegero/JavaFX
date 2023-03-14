package TelegramBot.service.api;

import TelegramBot.ApiBuilder;
import TelegramBot.ApiEndPoints;
import TelegramBot.model.*;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.List;

public class ApiServiceImpl implements ApiService {
    String user = "Api Test";

    String token = "Bearer NZ3WX5qX3cMBhJfeM_ReZ6szdhMk734c2yg6VVNe42w";

    @SneakyThrows
    @Override
    public RootAnime searchAnime(String keyword) {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, token, user);
        List<RootAnime> list = apiEndPoints.searchAnime(keyword).execute().body();
        return list.get(0);
    }

    @SneakyThrows
    @Override
    public RootManga searchManga(String keyword) {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, token, user);
        List<RootManga> list = apiEndPoints.searchManga(keyword).execute().body();
        return list.get(0);
    }

    @SneakyThrows
    @Override
    public RootCharacter searchCharacter(String keyword) {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, token, user);
        List<RootCharacter> list = apiEndPoints.searchCharacter(keyword).execute().body();
        return list.get(0);
    }

    @SneakyThrows
    @Override
    public List<String> searchAnimeUrl() {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, token, user);
        List<String> list = new java.util.ArrayList<>(apiEndPoints.searchAnimeUrl(1, 250).execute().body().stream()
                .filter(c -> c.getAnime().kind.equals("tv") && c.getStatus().equals("completed"))
                .map(c -> "shikimori.one" + c.getAnime().getImage().getOriginal())
                .toList());
        Collections.shuffle(list);
        return list.stream()
                .limit(16)
                .toList();
    }

    @SneakyThrows
    @Override
    public void changeRating(String rating, String id, String type, String userId) {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, token, user);
        RootAnime rootAnime = new RootAnime();
        rootAnime.setUser_rate(new UserRate(rating, id, type, userId));
        apiEndPoints.changeRating(rootAnime).execute().body();
    }

    @SneakyThrows
    @Override
    public void sendDm(String message, String fromId, String toId) {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, token, user);
        RootAnime rootAnime = new RootAnime();
        rootAnime.setMessage(new Message(message, fromId, "Private", toId));
        rootAnime.setFrontend("false");
        apiEndPoints.sendDm(rootAnime).execute().body();
    }

    @SneakyThrows
    @Override
    public RootUser getId(String nickname) {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, token, user);
        RootUser rootUsers = apiEndPoints.getId(nickname).execute().body();
        return rootUsers;
    }
}
