
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Tickets;
import services.CostumerFlightService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CostumerFlightsController {

    @FXML private ToggleGroup tripTypeGroup;
    @FXML private RadioButton returnRadio;
    @FXML private RadioButton oneWayRadio;
    @FXML private TextField txtDeparture;
    @FXML private TextField txtDestination;
    @FXML private DatePicker dpDepartureDate;
    @FXML private DatePicker dpReturnDate;
    @FXML private Button btnAddPassenger;
    @FXML private Button btnRemovePassenger;
    @FXML private Text passengerText;
    @FXML private Button btnSearch;

    private int passengerCount = 1;
    private final CostumerFlightService ticketFlightService = new CostumerFlightService();

    @FXML
    private void initialize() {
        tripTypeGroup = new ToggleGroup();
        returnRadio.setToggleGroup(tripTypeGroup);
        oneWayRadio.setToggleGroup(tripTypeGroup);

        tripTypeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (tripTypeGroup.getSelectedToggle() != null) {
                String trip = ((RadioButton) tripTypeGroup.getSelectedToggle()).getText();
                dpReturnDate.setDisable(trip.equalsIgnoreCase("One way"));
            }
        });

        dpReturnDate.setDisable(oneWayRadio.isSelected());
        passengerText.setText(String.valueOf(passengerCount));
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

        if (departure == null || destination == null || departureDate == null ||
                departure.isEmpty() || destination.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill all fields.");
            return;
        }

        List<Tickets> matchingTickets = ticketFlightService.findMatchingTickets(
                departure, destination, departureDate, passengerCount
        );

        if (!matchingTickets.isEmpty()) {
            Tickets ticket = matchingTickets.get(0);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/TicketCard.fxml"));
                Parent root = loader.load();

                controller.TicketCardController controller = loader.getController();
                controller.setTicketData(ticket);

                Stage stage = (Stage) btnSearch.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.INFORMATION, "No Flights", "No matching flights found.");
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
            Parent root = loader.load();
            Scene currentScene = ((Button) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
