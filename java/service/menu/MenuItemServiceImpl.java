package service.menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Students;
import service.database.DatabaseService;
import service.database.DatabaseServiceImpl;
import service.menu.MenuItemService;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    Stage stage = new Stage();
    DatabaseService databaseService = new DatabaseServiceImpl();
    ObservableList<Students> langs = FXCollections.observableArrayList(databaseService.findAll());


    @Override
    public MenuItem add(Text average, Text sum) {
        MenuItem add = new MenuItem("Добавить");
        add.setOnAction(e -> {
            final Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(stage);
            Text text = new Text("Что написать?");
            Text fullNameText = new Text("Имя, фамилия");
            Text instituteText = new Text("Название института");
            Text groupText = new Text("Группа");
            Text courseText = new Text("Курс");
            Text averageScoreText = new Text("Средний балл");
            TextField fullName = new TextField();
            TextField institute = new TextField();
            TextField group = new TextField();
            TextField course = new TextField();
            TextField averageScore = new TextField();
            Button btn = new Button();
            btn.setText("Сохранить");
            btn.setOnAction(actionEvent -> {
                databaseService.add(fullName.getText()
                        , institute.getText()
                        , group.getText()
                        , Integer.parseInt(course.getText())
                        , Double.parseDouble(averageScore.getText()));
                langs.clear();
                langs.addAll(databaseService.findAll());
                average.setText("Средний балл: " + databaseService.average());
                sum.setText("Сумма баллов: " + databaseService.sum());
            });
            VBox additionalRoot = new VBox(fullNameText
                    , fullName
                    , instituteText, institute
                    , groupText, group
                    , courseText, course
                    , averageScoreText, averageScore
                    , btn);
            Scene additionalScene = new Scene(additionalRoot, 400, 400);
            popup.setTitle("Добавить ученика");
            popup.setScene(additionalScene);
            popup.show();
        });
        return add;
    }

    @Override
    public MenuItem edit(Text average, Text sum) {
        MenuItem edit = new MenuItem("Изменить");
        edit.setOnAction(e -> {
            final Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(stage);
            Text text = new Text("Что написать?");
            Text idText = new Text("ID, которое хотите изменить");
            Text fullNameText = new Text("Имя, фамилия");
            Text instituteText = new Text("Название института");
            Text groupText = new Text("Группа");
            Text courseText = new Text("Курс");
            Text averageScoreText = new Text("Средний балл");
            TextField id = new TextField();
            TextField fullName = new TextField();
            TextField institute = new TextField();
            TextField group = new TextField();
            TextField course = new TextField();
            TextField averageScore = new TextField();
            Button btn = new Button();
            btn.setText("Сохранить");
            btn.setOnAction(actionEvent -> {
                databaseService.edit(Integer.parseInt(id.getText())
                        , fullName.getText()
                        , institute.getText()
                        , group.getText()
                        , Integer.parseInt(course.getText())
                        , Double.parseDouble(averageScore.getText()));
                langs.clear();
                langs.addAll(databaseService.findAll());
                average.setText("Средний балл: " + databaseService.average());
                sum.setText("Сумма баллов: " + databaseService.sum());
            });
            VBox additionalRoot = new VBox(idText, id
                    , fullNameText, fullName
                    , instituteText, institute
                    , groupText, group
                    , courseText, course
                    , averageScoreText, averageScore
                    , btn);
            Scene additionalScene = new Scene(additionalRoot, 400, 400);
            popup.setTitle("Изменить ученика");
            popup.setScene(additionalScene);
            popup.show();
        });
        return edit;
    }

    @Override
    public MenuItem delete(Text average, Text sum) {
        MenuItem delete = new MenuItem("Удалить");
        delete.setOnAction(e -> {
            final Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(stage);
            Text text = new Text("Что написать?");
            Text idText = new Text("ID, которое хотите удалить");
            TextField id = new TextField();
            Button btn = new Button();
            btn.setText("Сохранить");
            btn.setOnAction(actionEvent -> {
                databaseService.delete(Integer.parseInt(id.getText()));
                langs.clear();
                langs.addAll(databaseService.findAll());
                average.setText("Средний балл: " + databaseService.average());
                sum.setText("Сумма баллов: " + databaseService.sum());
            });
            VBox additionalRoot = new VBox(idText, id
                    , btn);
            Scene additionalScene = new Scene(additionalRoot, 400, 400);
            popup.setTitle("Удалить ученика");
            popup.setScene(additionalScene);
            popup.show();
        });
        return delete;
    }

    @Override
    public Button search(TextField searchBar, Text average, Text sum) {
        Button searchButton = new Button();
        searchButton.setText("Искать");
        searchButton.setOnAction(actionEvent -> {
            langs.clear();
            langs.addAll(databaseService.searchFullName(searchBar.getText()));
            average.setText("Средний балл: " + databaseService.average());
            sum.setText("Сумма баллов: " + databaseService.sum());
        });
        return searchButton;
    }

    @Override
    public Button reset(Text average, Text sum) {
        Button resetButton = new Button();
        resetButton.setText("Сброс");
        resetButton.setOnAction(actionEvent -> {
            langs.clear();
            langs.addAll(databaseService.findAll());
            average.setText("Средний балл: " + databaseService.average());
            sum.setText("Сумма баллов: " + databaseService.sum());
        });
        return resetButton;
    }
}
