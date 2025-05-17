package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Tickets;
import services.CostumerFlightService;
import services.SceneManager;

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
            System.out.println("Please fill in all fields.");
            return;
        }

        System.out.println("Searching flight for:");
        System.out.println("From: " + departure + " To: " + destination);
        System.out.println("Date: " + departureDate + " | Passengers: " + passengerCount);

        List<Tickets> matchingTickets = ticketFlightService.findMatchingTickets(
                departure, destination, departureDate, passengerCount
        );

        if (!matchingTickets.isEmpty()) {
            Tickets ticket = matchingTickets.get(0);
            SceneManager.getInstance().setData("selectedTicket", ticket);
            SceneManager.getInstance().switchScene("/Views/reserve_ticket.fxml");
        } else {
            System.out.println("No matching tickets found.");
        }
    }
}
