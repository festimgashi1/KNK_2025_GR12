package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.Tickets;
import services.SceneManager;
import session.CustomerSession;
import session.TicketSession;

public class CustomerNavbarController {

    @FXML
    public void showFlights(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
    }

    @FXML
    public void showReservation(ActionEvent event) {

        Tickets ticket = TicketSession.getInstance().getSelectedTicket();

        if (ticket == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Ticket");
            alert.setHeaderText("You need to buy a ticket first.");
            alert.setContentText("Please search and buy a flight ticket before accessing reservations.");
            alert.showAndWait();
            return;
        }

        // Nëse ekziston biletë, vazhdo në Check-In view
        SceneManager.getInstance().switchScene("/Views/CheckInView.fxml");
    }

    @FXML
    public void showFeedback(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/feedback.fxml");
    }

    @FXML
    public void goLogIn(ActionEvent event) {
        if (CustomerSession.getInstance().getCurrentCostumer() != null) {
            SceneManager.getInstance().switchScene("/Views/costumer_profile.fxml");
        } else {
            SceneManager.getInstance().switchScene("/Views/login.fxml");
        }
    }

    @FXML
    public void handleSignOut(ActionEvent event) {
        CustomerSession.getInstance().clear();
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }

    @FXML
    public void showAllFlights(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_all_flights.fxml");
    }
}
