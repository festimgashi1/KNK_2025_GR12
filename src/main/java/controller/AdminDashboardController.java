package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import services.SceneManager;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private StackPane contentPane;

    private Node loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(getClass().getResource(fxml));
    }

    @FXML
    private void showStatistics() throws IOException {
        SceneManager.getInstance().switchScene("/Views/admin_home.fxml");    }

    @FXML
    private void showFlights() throws IOException {
        SceneManager.getInstance().switchScene("/Views/admin_flights.fxml");    }

    @FXML
    private void showAirlines() throws IOException {
        SceneManager.getInstance().switchScene("/Views/admin_airlines.fxml");
    }

    @FXML
    public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/log_in.fxml");
    }
}
