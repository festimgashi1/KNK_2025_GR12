package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.SceneManager;

public class AirlineController {
    @FXML
    public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/log_in.fxml");
    }
}
