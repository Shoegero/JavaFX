package TelegramBot.service.database;

import lombok.SneakyThrows;

public interface DatabaseService {
    @SneakyThrows
    void add(String name, String score, String status, int episodes, String release, String url);
}
