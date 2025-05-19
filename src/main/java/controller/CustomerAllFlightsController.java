package controller;



import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Flights;
import repository.AdminFlightsRepository;

import java.util.List;

public class CustomerAllFlightsController {

    @FXML private TableView<Flights> flightsTable;
    @FXML private TableColumn<Flights, Integer> colFlightNumber;

    @FXML private TableColumn<Flights, String> colDepartureAirport;
    @FXML private TableColumn<Flights, String> colArrivalAirport;
    @FXML private TableColumn<Flights, String> colDepartureTime;
    @FXML private TableColumn<Flights, String> colArrivalTime;
    @FXML private TableColumn<Flights, String> colDuration;
    @FXML private TableColumn<Flights, String> colStatus;

    @FXML private Label statusLabel;

    private final AdminFlightsRepository flightsRepository = new AdminFlightsRepository();

    @FXML
    public void initialize() {
        colFlightNumber.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getFlightNumber()).asObject());

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

    private void showStatusMessage(String message, String type) {
        statusLabel.setText(message);

        switch (type.toLowerCase()) {
            case "success" -> statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
            case "error" -> statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            case "warning" -> statusLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-weight: bold;");
            default -> statusLabel.setStyle("-fx-text-fill: black;");
        }
    }
}
