package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Flights;
import services.FlightService;

public class EditFlightController {

    @FXML private TextField txtFlightNumber;
    @FXML private TextField txtPlaneId;
    @FXML private TextField txtDepartureAirport;
    @FXML private TextField txtArrivalAirport;
    @FXML private TextField txtDepartureTime;
    @FXML private TextField txtArrivalTime;
    @FXML private TextField txtDuration;
    @FXML private TextField txtStatus;

    private final FlightService flightService = new FlightService();
    private Flights flightToEdit;

    public void setFlight(Flights flight) {
        this.flightToEdit = flight;

        txtFlightNumber.setText(String.valueOf(flight.getFlightNumber()));
        txtPlaneId.setText(String.valueOf(flight.getPlaneid()));
        txtDepartureAirport.setText(flight.getDepartureAirport());
        txtArrivalAirport.setText(flight.getArrivalAirport());
        txtDepartureTime.setText(flight.getDepartureTime());
        txtArrivalTime.setText(flight.getArrivalTime());
        txtDuration.setText(flight.getDuration());
        txtStatus.setText(flight.getStatus());
    }

    @FXML
    public void handleUpdate() {
        flightToEdit.setPlaneid(Integer.parseInt(txtPlaneId.getText()));
        flightToEdit.setDepartureAirport(txtDepartureAirport.getText());
        flightToEdit.setArrivalAirport(txtArrivalAirport.getText());
        flightToEdit.setDepartureTime(txtDepartureTime.getText());
        flightToEdit.setArrivalTime(txtArrivalTime.getText());
        flightToEdit.setDuration(txtDuration.getText());
        flightToEdit.setStatus(txtStatus.getText());

        boolean updated = flightService.updateFlight(flightToEdit);
        if (updated) {
            ((Stage) txtStatus.getScene().getWindow()).close();
        }
    }
}

