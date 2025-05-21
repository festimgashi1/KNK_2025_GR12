package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import model.Flights;
import repository.AirlineAllFlightsRepository;
import services.LanguageManager;

import java.util.List;
import java.util.ResourceBundle;

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

    @FXML private Button btnToday;
    @FXML private Button btnAllFlights;
    @FXML private Label lblDisplay;
    @FXML private Label lblArrivals;
    @FXML private Label lblDepartures;

    private final AirlineAllFlightsRepository flightRepo = new AirlineAllFlightsRepository();
    private final String CURRENT_AIRPORT = "Prishtina";

    @FXML
    public void initialize() {
        setupArrivalTable();
        setupDepartureTable();
        loadTranslations();
        LanguageManager.getInstance().addListener(this::loadTranslations);
        showToday();
    }

    private void loadTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        btnToday.setText(bundle.getString("flights.today"));
        btnAllFlights.setText(bundle.getString("flights.all"));
        lblDisplay.setText(bundle.getString("flights.display"));
        lblArrivals.setText("✈ " + bundle.getString("flights.arrivals"));
        lblDepartures.setText("✈ " + bundle.getString("flights.departures"));

        colStaArrivals.setText(bundle.getString("col.sta"));
        colEstArrivals.setText(bundle.getString("col.est"));
        colFlightArrivals.setText(bundle.getString("col.flight"));
        colFromArrivals.setText(bundle.getString("col.from"));
        colStatusArrivals.setText(bundle.getString("col.status"));
        colAirlineArrivals.setText(bundle.getString("col.airline"));

        colStaDepartures.setText(bundle.getString("col.sta"));
        colEstDepartures.setText(bundle.getString("col.est"));
        colFlightDepartures.setText(bundle.getString("col.flight"));
        colToDepartures.setText(bundle.getString("col.to"));
        colStatusDepartures.setText(bundle.getString("col.status"));
        colAirlineDepartures.setText(bundle.getString("col.airline"));
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
