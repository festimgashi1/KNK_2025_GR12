package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Flights;
import services.FlightService;
import session.AirlineSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddFlightController {

    @FXML private TextField txtFlightNumber;
    @FXML private TextField txtAirlineId;
    @FXML private TextField txtPlaneId;
    @FXML private TextField txtDepartureAirport;
    @FXML private TextField txtArrivalAirport;
    @FXML private TextField txtDepartureTime;
    @FXML private TextField txtArrivalTime;
    @FXML private TextField txtDuration;
    @FXML private TextField txtStatus;

    @FXML private Label lblError;
    @FXML private Label lblSuccess; // Label për mesazhin e suksesit

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    int airlineId = AirlineSession.getAirlineId();
    private final FlightService flightService = new FlightService();

    @FXML
    public void handleSubmit() {
        String departureTime = txtDepartureTime.getText();
        String arrivalTime = txtArrivalTime.getText();

        // Fshij mesazhet fillimisht
        lblError.setVisible(false);
        lblSuccess.setVisible(false);

        if (!isValidDateTime(departureTime) || !isValidDateTime(arrivalTime)) {
            lblError.setText("The date format is not written correctly. Use YYYY-MM-DD HH:mm");
            lblError.setVisible(true);
            return;
        }

        try {
            Flights flight = new Flights(
                    Integer.parseInt(txtFlightNumber.getText()),
                    airlineId,
                    Integer.parseInt(txtPlaneId.getText()),
                    txtDepartureAirport.getText(),
                    txtArrivalAirport.getText(),
                    departureTime,
                    arrivalTime,
                    txtDuration.getText(),
                    txtStatus.getText()
            );

            flightService.createFlight(flight);

            lblSuccess.setText("Flight added successfully!");
            lblSuccess.setVisible(true);
            clearForm(); // pastro fushat pas suksesit

        } catch (NumberFormatException e) {
            lblError.setText("The numbers (Flight Number / Plane ID) are in the wrong format.");
            lblError.setVisible(true);
        }
    }

    private boolean isValidDateTime(String input) {
        try {
            LocalDateTime.parse(input, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void clearForm() {
        txtFlightNumber.clear();
        txtPlaneId.clear();
        txtDepartureAirport.clear();
        txtArrivalAirport.clear();
        txtDepartureTime.clear();
        txtArrivalTime.clear();
        txtDuration.clear();
        txtStatus.clear();
    }
}
