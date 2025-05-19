package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Flights;
import repository.AirlineAllFlightsRepository;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class AirlineAllFlightsController {

    @FXML private TableView<Flights> tableViewArrivals;
    @FXML private TableColumn<Flights, String> colStaArrivals;
    @FXML private TableColumn<Flights, String> colEstArrivals;
    @FXML private TableColumn<Flights, String> colFlightArrivals;
    @FXML private TableColumn<Flights, String> colFromArrivals;
    @FXML private TableColumn<Flights, String> colStatusArrivals;
    @FXML private TableColumn<Flights, String> colAirlineArrivals;

    @FXML private TableView<Flights> tableViewDepartures;
    @FXML private TableColumn<Flights, String> colStaDepartures;
    @FXML private TableColumn<Flights, String> colEstDepartures;
    @FXML private TableColumn<Flights, String> colFlightDepartures;
    @FXML private TableColumn<Flights, String> colToDepartures;
    @FXML private TableColumn<Flights, String> colStatusDepartures;
    @FXML private TableColumn<Flights, String> colAirlineDepartures;

    private final AirlineAllFlightsRepository flightRepo = new AirlineAllFlightsRepository();
    private final String CURRENT_AIRPORT = "Prishtina";

    @FXML
    public void initialize() {
        setupArrivalTable();
        setupDepartureTable();
        showToday();
    }

    private void setupArrivalTable() {
        colStaArrivals.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDepartureTime()));
        colEstArrivals.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getArrivalTime()));
        colFlightArrivals.setCellValueFactory(f -> new SimpleStringProperty("FL-" + f.getValue().getFlightNumber()));
        colFromArrivals.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
        colStatusArrivals.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAirlineArrivals.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    }

    private void setupDepartureTable() {
        colStaDepartures.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDepartureTime()));
        colEstDepartures.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getArrivalTime()));
        colFlightDepartures.setCellValueFactory(f -> new SimpleStringProperty("FL-" + f.getValue().getFlightNumber()));
        colToDepartures.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        colStatusDepartures.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAirlineDepartures.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    }

    @FXML
    private void showToday() {
        List<Flights> arrivals = flightRepo.getArrivalsToday(CURRENT_AIRPORT);
        List<Flights> departures = flightRepo.getDeparturesToday(CURRENT_AIRPORT);
        tableViewArrivals.getItems().setAll(arrivals);
        tableViewDepartures.getItems().setAll(departures);
    }

    @FXML
    private void showAllFlights() {
        List<Flights> arrivals = flightRepo.getAllArrivals(CURRENT_AIRPORT);
        List<Flights> departures = flightRepo.getAllDepartures(CURRENT_AIRPORT);
        tableViewArrivals.getItems().setAll(arrivals);
        tableViewDepartures.getItems().setAll(departures);
    }
}
