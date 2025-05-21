package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Tickets;
import services.LanguageManager;
import services.SceneManager;
import session.CustomerSession;
import session.TicketSession;

import java.util.ResourceBundle;

public class CustomerNavbarController {

    @FXML private Label lblWelcome;
    @FXML private Button btnFindFlights;
    @FXML private Button btnAllFlights;
    @FXML private Button btnCheckIn;
    @FXML private Button btnFeedback;
    @FXML private Button btnProfile;
    @FXML private Button btnSignOut;

    @FXML
    public void initialize() {
        applyTranslations();

        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblWelcome.setText(bundle.getString("navbar.welcome"));
        btnFindFlights.setText("🛫 " + bundle.getString("navbar.findflights"));
        btnAllFlights.setText("🛫 " + bundle.getString("navbar.allflights"));
        btnCheckIn.setText("📝 " + bundle.getString("navbar.checkin"));
        btnFeedback.setText("💬 " + bundle.getString("navbar.feedback"));
        btnProfile.setText("👤 " + bundle.getString("navbar.profile"));
        btnSignOut.setText("🚪 " + bundle.getString("navbar.signout"));
    }

    @FXML
    public void showFlights(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
    }

    @FXML
    public void showAllFlights(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_all_flights.fxml");
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
}
