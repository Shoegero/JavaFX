package TelegramBot.service.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseServiceImpl implements DatabaseService {
    final String DB_URL = "jdbc:postgresql://192.168.0.101:8492/postgres";
    final String USER = "root";
    final String PASS = "3221";
    final String CREATE = "INSERT into animes values(nextval('\"animes_id_seq\"'::regclass),?,?,?,?,?,?)";

    @SneakyThrows
    @Override
    public void add(String name, String score, String status, int episodes, String release, String url) {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(CREATE);
        stmt.setString(1, name);
        stmt.setString(2, score);
        stmt.setString(3, status);
        stmt.setInt(4, episodes);
        stmt.setString(5, release);
        stmt.setString(6, url);
        stmt.executeUpdate();
    }
}
