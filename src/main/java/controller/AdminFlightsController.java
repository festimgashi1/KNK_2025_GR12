package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Flights;
import repository.AdminFlightsRepository;

import java.util.List;

public class AdminFlightsController {

    @FXML private TableView<Flights> flightsTable;
    @FXML private TableColumn<Flights, Integer> colFlightNumber;
    @FXML private TableColumn<Flights, Integer> colAirlineId;
    @FXML private TableColumn<Flights, Integer> colPlaneId;
    @FXML private TableColumn<Flights, String> colDepartureAirport;
    @FXML private TableColumn<Flights, String> colArrivalAirport;
    @FXML private TableColumn<Flights, String> colDepartureTime;
    @FXML private TableColumn<Flights, String> colArrivalTime;
    @FXML private TableColumn<Flights, String> colDuration;
    @FXML private TableColumn<Flights, String> colStatus;

    private final AdminFlightsRepository flightsRepository = new AdminFlightsRepository();

    @FXML
    public void initialize() {
        colFlightNumber.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getFlightNumber()).asObject());
        colAirlineId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAirlineid()).asObject());
        colPlaneId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPlaneid()).asObject());
        colDepartureAirport.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDepartureAirport()));
        colArrivalAirport.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getArrivalAirport()));
        colDepartureTime.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDepartureTime()));
        colArrivalTime.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getArrivalTime()));
        colDuration.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDuration()));
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        loadFlights();
    }

    private void loadFlights() {
        List<Flights> flights = flightsRepository.getAll();
        flightsTable.getItems().setAll(flights);
    }

    @FXML
    public void handleDeleteFlight() {
        Flights selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            boolean deleted = flightsRepository.deleteByFlightNumber(selectedFlight.getFlightNumber());
            if (deleted) {
                flightsTable.getItems().remove(selectedFlight);
                showAlert("Flight deleted", "The flight has been successfully removed.");
            } else {
                showAlert("Error", "Failed to delete flight.");
            }
        } else {
            showAlert("No Selection", "Please select a flight to delete.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
