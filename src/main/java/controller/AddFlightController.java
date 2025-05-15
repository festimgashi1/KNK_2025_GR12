package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Flights;
import services.FlightService;

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

    private final FlightService flightService = new FlightService();

    @FXML
    public void handleSubmit() {
        try {
            Flights flight = new Flights(
                    Integer.parseInt(txtFlightNumber.getText()),
                    Integer.parseInt(txtAirlineId.getText()),
                    Integer.parseInt(txtPlaneId.getText()),
                    txtDepartureAirport.getText(),
                    txtArrivalAirport.getText(),
                    txtDepartureTime.getText(),
                    txtArrivalTime.getText(),
                    txtDuration.getText(),
                    txtStatus.getText()
            );

            flightService.createFlight(flight);
            System.out.println("Flight added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        }
    }
}

