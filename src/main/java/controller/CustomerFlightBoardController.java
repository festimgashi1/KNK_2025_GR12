package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Flights;
import repository.CustomerFlightBoardRepository;
import javafx.beans.property.SimpleStringProperty;
import services.LanguageManager;

import java.util.List;
import java.util.ResourceBundle;

public class CustomerFlightBoardController {

    @FXML private TableView<Flights> tableViewArrivals;
    @FXML private TableColumn<Flights, String> colStaArrivals;
    @FXML private TableColumn<Flights, String> colEstArrivals;
    @FXML private TableColumn<Flights, String> colFlightArrivals;
    @FXML private TableColumn<Flights, String> colFromArrivals;
    @FXML private TableColumn<Flights, String> colStatusArrivals;

    @FXML private TableView<Flights> tableViewDepartures;
    @FXML private TableColumn<Flights, String> colStaDepartures;
    @FXML private TableColumn<Flights, String> colEstDepartures;
    @FXML private TableColumn<Flights, String> colFlightDepartures;
    @FXML private TableColumn<Flights, String> colToDepartures;
    @FXML private TableColumn<Flights, String> colStatusDepartures;

    @FXML private Label lblArrivals;
    @FXML private Label lblDepartures;
    @FXML private Label lblDisplay;
    @FXML private Button btnToday;
    @FXML private Button btnAllFlights;

    private final CustomerFlightBoardRepository flightRepo = new CustomerFlightBoardRepository();
    private final String CURRENT_AIRPORT = "Prishtina";

    @FXML
    public void initialize() {
        setupArrivalTable();
        setupDepartureTable();
        showToday();
        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void setupArrivalTable() {
        colStaArrivals.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDepartureTime()));
        colEstArrivals.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getArrivalTime()));
        colFlightArrivals.setCellValueFactory(f -> new SimpleStringProperty("FL-" + f.getValue().getFlightNumber()));
        colFromArrivals.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
        colStatusArrivals.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setupDepartureTable() {
        colStaDepartures.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDepartureTime()));
        colEstDepartures.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getArrivalTime()));
        colFlightDepartures.setCellValueFactory(f -> new SimpleStringProperty("FL-" + f.getValue().getFlightNumber()));
        colToDepartures.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        colStatusDepartures.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        lblDisplay.setText(bundle.getString("display"));
        btnToday.setText(bundle.getString("today"));
        btnAllFlights.setText(bundle.getString("all.flights"));
        lblArrivals.setText(bundle.getString("arrivals"));
        lblDepartures.setText(bundle.getString("departures"));
        colStaArrivals.setText(bundle.getString("sta"));
        colEstArrivals.setText(bundle.getString("est"));
        colFlightArrivals.setText(bundle.getString("flight"));
        colFromArrivals.setText(bundle.getString("from"));
        colStatusArrivals.setText(bundle.getString("status"));

        colStaDepartures.setText(bundle.getString("sta"));
        colEstDepartures.setText(bundle.getString("est"));
        colFlightDepartures.setText(bundle.getString("flight"));
        colToDepartures.setText(bundle.getString("to"));
        colStatusDepartures.setText(bundle.getString("status"));
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
