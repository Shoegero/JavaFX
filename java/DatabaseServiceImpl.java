import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseServiceImpl implements DatabaseService {
    final String DB_URL = "jdbc:postgresql://192.168.0.104:8492/postgres";
    final String USER = "root";
    final String PASS = "3221";
    final String FIND_ALL = "SELECT * from students ORDER BY id";
    final String CREATE = "INSERT into students values(nextval('\"students_id_seq\"'::regclass),?,?,?,?,?)";
    final String UPDATE = "UPDATE students SET full_name = ?,institute = ?,group_number = ?,course = ?,average_score = ? WHERE id = ?";
    final String DELETE = "DELETE from students where id = ?";
    final String FIND = "SELECT * from students WHERE lower(full_name) = lower(?)";


    @SneakyThrows
    @Override
    public void add(String fullName, String institute, String group, int course, double averageScore) {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(CREATE);
        stmt.setString(1, fullName);
        stmt.setString(2, institute);
        stmt.setString(3, group);
        stmt.setInt(4, course);
        stmt.setDouble(5, averageScore);
        stmt.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void edit(int id, String fullName, String institute, String group, int course, double averageScore) {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(UPDATE);
        stmt.setInt(6, id);
        stmt.setString(1, fullName);
        stmt.setString(2, institute);
        stmt.setString(3, group);
        stmt.setInt(4, course);
        stmt.setDouble(5, averageScore);
        stmt.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void delete(int id) {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(DELETE);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @SneakyThrows
    @Override
    public List<Students> searchFullName(String fullName) {
        List<Students> list = new ArrayList<>();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(FIND);
        stmt.setString(1, fullName);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Students returningStudents = new Students();
            returningStudents.setId(rs.getInt("id"));
            returningStudents.setFullName(rs.getString("full_name"));
            returningStudents.setInstitute(rs.getString("institute"));
            returningStudents.setGroup(rs.getString("group_number"));
            returningStudents.setCourse(rs.getInt("course"));
            returningStudents.setAverageScore(rs.getDouble("average_score"));
            list.add(returningStudents);
        }
        return list;
    }


    @SneakyThrows
    @Override
    public double average() {
        List<Students> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FIND_ALL);) {
            while (rs.next()) {
                list.add(new Students(rs.getInt("id")
                        , rs.getString("full_name")
                        , rs.getString("institute")
                        , rs.getString("group_number")
                        , rs.getInt("course")
                        , rs.getDouble("average_score")));
            }
        }
        return list.stream()
                .mapToDouble(Students::getAverageScore)
                .average()
                .orElse(0.0);
    }

    @SneakyThrows
    @Override
    public double sum() {
        double sum = 0;
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(FIND_ALL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            double i = rs.getDouble("average_score");
            sum = sum + i;
        }
        return sum;
    }

    @SneakyThrows
    @Override
    public List<Students> findAll() {
        List<Students> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FIND_ALL);) {
            while (rs.next()) {
                list.add(new Students(rs.getInt("id")
                        , rs.getString("full_name")
                        , rs.getString("institute")
                        , rs.getString("group_number")
                        , rs.getInt("course")
                        , rs.getDouble("average_score")));
            }
        }
        return list;
    }
}
