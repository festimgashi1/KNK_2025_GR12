package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Tickets;
import services.SceneManager;
import services.TicketFlightService;

import java.time.LocalDate;
import java.util.Optional;

public class CostumerFlightsController {

    @FXML private ToggleGroup tripTypeGroup;
    @FXML private RadioButton returnRadio;
    @FXML private RadioButton oneWayRadio;

    @FXML private TextField txtDeparture;
    @FXML private TextField txtDestination;
    @FXML private DatePicker dpDepartureDate;
    @FXML private DatePicker dpReturnDate;

    @FXML private Button btnAddPassenger;
    @FXML private Text passengerText;
    @FXML private Button btnSearch;

    private int passengerCount = 1;

    private final TicketFlightService ticketFlightService = new TicketFlightService();

    @FXML
    private void initialize() {
        // Vendos ToggleGroup manualisht
        tripTypeGroup = new ToggleGroup();
        returnRadio.setToggleGroup(tripTypeGroup);
        oneWayRadio.setToggleGroup(tripTypeGroup);

        // Listener për të aktivizuar/deaktivuar returnDate
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

        Optional<Tickets> foundTicket = ticketFlightService.findMatchingTicket(
                departure, destination, departureDate, passengerCount
        );

        if (foundTicket.isPresent()) {
            Tickets ticket = foundTicket.get();

            // Ruaj biletën dhe kalo në rezervim
            SceneManager.getInstance().setData("selectedTicket", ticket);
            SceneManager.getInstance().switchScene("/Views/reserve_ticket.fxml");
        } else {
            System.out.println("No matching flight found.");
        }
    }
}
