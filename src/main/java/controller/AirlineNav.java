package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import services.SceneManager;
import session.AirlineSession;

public class AirlineNav {

    @FXML private VBox navBox;
    @FXML private Label lblAirlineName; // ➕ për emrin e airline-it

    private void setActiveButton(Button clickedButton) {
        for (var node : navBox.getChildren()) {
            if (node instanceof Button btn) {
                btn.getStyleClass().remove("nav-button-active");
            }
        }
        clickedButton.getStyleClass().add("nav-button-active");
    }

    @FXML
    public void initialize() {
        lblAirlineName.setText(AirlineSession.getAirlineName()); // ➕ vendos emrin
    }

    @FXML
    public void goToAddFlight(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/add_flight.fxml");
    }

    @FXML
    public void goToManageFlights(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/manage_flights.fxml");
    }

    @FXML
    public void goToAllFlights(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/all_flights.fxml");
    }

    @FXML
    public void goToTickets(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/tickets.fxml");
    }

    @FXML
    public void goToStatistics(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/statistics.fxml");
    }

    @FXML
    public void goToProfile(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/profile.fxml");
    }
}
