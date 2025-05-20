package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

        // ✅ Siguron që VBox navBox të ngjitet komplet lart sapo të jetë ngarkuar
        navBox.setTranslateY(0);
        navBox.setPadding(new Insets(0, 10, 20, 10));
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
        SceneManager.getInstance().switchScene("/Views/airline_all_flights.fxml");
    }

    @FXML
    public void goToTickets(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/add_tickets.fxml");
    }

    @FXML
    public void goToStatistics(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/airline_statistics.fxml");
    }

    @FXML
    public void goToProfile(ActionEvent event) {
        setActiveButton((Button) event.getSource());
        SceneManager.getInstance().switchScene("/Views/airline_profile.fxml");
    }
    @FXML
    private void handleSignOut() {
        AirlineSession.clear();
        SceneManager.getInstance().switchScene("/Views/login.fxml");    }

    }
