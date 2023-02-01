package service.database;

import model.Students;

import java.util.List;

public interface DatabaseService {

    void add(String fullName, String institute, String group, int course, double averageScore);

    void edit(int id, String fullName, String institute, String group, int course, double averageScore);

    void delete(int id);

    List<Students> searchFullName(String fullName);

    double average();

    double sum();

    List<Students> findAll();
}
