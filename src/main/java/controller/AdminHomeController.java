package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.SceneManager;

public class AdminHomeController {

    @FXML
    public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/log_in.fxml");
    }

    @FXML
    public void goHome(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/admin_home.fxml");
    }

    @FXML
    public void goFlights(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/flights.fxml");
    }

    @FXML
    public void goReservations(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/reservations.fxml");
    }

    @FXML
    public void goAirlines(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/admin_airlines.fxml");
    }
}
