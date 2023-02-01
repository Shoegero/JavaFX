package service;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public interface MenuItemService {
    MenuItem add(Text average, Text sum);

    MenuItem edit(Text average, Text sum);

    MenuItem delete(Text average, Text sum);


    Button search(TextField searchBar, Text average, Text sum);


    Button reset(Text average, Text sum);
}
