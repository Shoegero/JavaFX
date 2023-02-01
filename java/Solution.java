import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import model.Students;
import service.database.DatabaseService;
import service.database.DatabaseServiceImpl;
import service.menu.MenuItemService;
import service.menu.MenuItemServiceImpl;

public class Solution extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @SneakyThrows
    @Override
    public void start(Stage stage) {
        DatabaseService databaseService = new DatabaseServiceImpl();
        ObservableList<Students> langs = FXCollections.observableArrayList(databaseService.findAll());
        TableView<Students> tableView = new TableView<>(langs);
        Text average = new Text("Средний балл: " + databaseService.average());
        Text sum = new Text("Сумма баллов: " + databaseService.sum());
        MenuItemService menuItemService = new MenuItemServiceImpl(stage, databaseService, langs);
        tableView.setPrefWidth(1366);
        tableView.setPrefHeight(768);

        setTableColumns(tableView);

        MenuBar menuBar = new MenuBar();
        Menu studentsMenu = new Menu("Студенты");


        MenuItem add = menuItemService.add(average, sum);
        MenuItem edit = menuItemService.edit(average, sum);
        MenuItem delete = menuItemService.delete(average, sum);

        studentsMenu.getItems().add(add);
        studentsMenu.getItems().add(edit);
        studentsMenu.getItems().add(delete);

        TextField searchBar = new TextField("Поиск по имени");
        Button searchButton = menuItemService.search(searchBar, average, sum);
        Button resetButton = menuItemService.reset(average, sum);

        menuBar.getMenus().addAll(studentsMenu);
        HBox hRoot = new HBox(menuBar, searchBar, searchButton, resetButton);
        VBox root = new VBox(hRoot, tableView, average, sum);
        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("model.Students Menu");
        stage.setScene(scene);
        stage.show();
    }

    private void setTableColumns(TableView<Students> tableView) {
        TableColumn<Students, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().add(idColumn);

        TableColumn<Students, String> nameColumn = new TableColumn<>("Full name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tableView.getColumns().add(nameColumn);

        TableColumn<Students, String> instituteColumn = new TableColumn<>("Institute");
        instituteColumn.setCellValueFactory(new PropertyValueFactory<>("institute"));
        tableView.getColumns().add(instituteColumn);

        TableColumn<Students, String> groupNumberColumn = new TableColumn<>("Group number");
        groupNumberColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        tableView.getColumns().add(groupNumberColumn);

        TableColumn<Students, Integer> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        tableView.getColumns().add(courseColumn);

        TableColumn<Students, Double> averageScoreColumn = new TableColumn<>("Average score");
        averageScoreColumn.setCellValueFactory(new PropertyValueFactory<>("averageScore"));
        tableView.getColumns().add(averageScoreColumn);
    }
}
