package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Tickets;
import services.CostumerFlightService;
import services.LanguageManager;
import services.SceneManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CostumerFlightsController {

    @FXML private TextField txtDeparture;
    @FXML private TextField txtDestination;
    @FXML private DatePicker dpDepartureDate;
    @FXML private Button btnAddPassenger;
    @FXML private Button btnRemovePassenger;
    @FXML private Text passengerText;
    @FXML private Button btnSearch;
    @FXML private VBox ticketListContainer;

    @FXML private Label lblDeparture;
    @FXML private Label lblDestination;
    @FXML private Label lblDate;
    @FXML private Label lblPassengers;
    @FXML private Label lblTitle;

    @FXML private Button btnAllFlights;

    private int passengerCount = 1;
    private final CostumerFlightService ticketFlightService = new CostumerFlightService();

    @FXML
    private void initialize() {
        passengerText.setText(String.valueOf(passengerCount));
        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        lblDeparture.setText(bundle.getString("departure"));
        lblDestination.setText(bundle.getString("destination"));
        lblDate.setText(bundle.getString("departure.date"));
        lblPassengers.setText(bundle.getString("passengers"));
        lblTitle.setText(bundle.getString("flights.title"));
        btnAllFlights.setText(bundle.getString("go.to.all.flights"));
        btnSearch.setText("🔍");
    }

    @FXML
    private void increasePassengerCount() {
        passengerCount++;
        passengerText.setText(String.valueOf(passengerCount));
    }

    @FXML
    private void decreasePassengerCount() {
        if (passengerCount > 1) {
            passengerCount--;
            passengerText.setText(String.valueOf(passengerCount));
        }
    }

    @FXML
    private void handleSearchClick() {
        String departure = txtDeparture.getText();
        String destination = txtDestination.getText();
        LocalDate departureDate = dpDepartureDate.getValue();
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        if (departure == null || destination == null || departureDate == null ||
                departure.isEmpty() || destination.isEmpty()) {
            showAlert(Alert.AlertType.WARNING,
                    bundle.getString("missing.fields"),
                    bundle.getString("please.fill.fields"));
            return;
        }

        List<Tickets> matchingTickets = ticketFlightService.searchAvailableTickets(
                departure, destination, departureDate, passengerCount
        );

        if (!matchingTickets.isEmpty()) {
            try {
                SceneManager.getInstance().setData("matchingTickets", matchingTickets);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/TicketList.fxml"));
                loader.setResources(bundle);
                Parent root = loader.load();
                Stage stage = (Stage) btnSearch.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.INFORMATION,
                    bundle.getString("no.flights"),
                    bundle.getString("no.matching.flights"));
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleGoToAllFlights(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/customer_all_flights.fxml"));
            loader.setResources(LanguageManager.getInstance().getResourceBundle());
            Parent root = loader.load();
            Scene currentScene = ((Button) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
