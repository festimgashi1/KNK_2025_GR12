package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.LanguageManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSelectorController {

    @FXML private MenuButton languageMenu;
    @FXML private MenuItem albanianItem;
    @FXML private MenuItem englishItem;

    @FXML
    public void initialize() {
        updateMenuText();

        LanguageManager.getInstance().addListener(this::updateMenuText);

        albanianItem.setOnAction(event -> {
            LanguageManager.getInstance().setLocale(new Locale("sq"));
            LanguageManager.getInstance().notifyListeners();
        });

        englishItem.setOnAction(event -> {
            LanguageManager.getInstance().setLocale(new Locale("en"));
            LanguageManager.getInstance().notifyListeners();
        });
    }

    private void updateMenuText() {
        Locale locale = LanguageManager.getInstance().getLocale();
        if (locale.getLanguage().equals("sq")) {
            languageMenu.setText("Shqip");
        } else {
            languageMenu.setText("English");
        }
    }
}
