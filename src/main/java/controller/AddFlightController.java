package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Flights;
import services.FlightService;
import services.LanguageManager;
import session.AirlineSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class AddFlightController {

    @FXML private Button btnAdd;

    @FXML private TextField txtFlightNumber, txtAirlineId, txtPlaneId, txtDepartureAirport, txtArrivalAirport,
            txtDepartureTime, txtArrivalTime, txtDuration, txtStatus;
    @FXML private Label lblError, lblSuccess;

    @FXML private Label lblTitle, lblFlightNumber, lblPlaneId, lblDepartureAirport, lblArrivalAirport,
            lblDepartureTime, lblArrivalTime, lblDuration, lblStatus;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    int airlineId = AirlineSession.getAirlineId();
    private final FlightService flightService = new FlightService();

    @FXML
    public void initialize() {
        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblTitle.setText(bundle.getString("flight.add.title"));
        lblFlightNumber.setText(bundle.getString("flight.number"));
        lblPlaneId.setText(bundle.getString("plane.id"));
        lblDepartureAirport.setText(bundle.getString("departure.airport"));
        lblArrivalAirport.setText(bundle.getString("arrival.airport"));
        lblDepartureTime.setText(bundle.getString("departure.time"));
        lblArrivalTime.setText(bundle.getString("arrival.time"));
        lblDuration.setText(bundle.getString("flight.duration"));
        lblStatus.setText(bundle.getString("flight.status"));

        btnAdd.setText(bundle.getString("flight.add.button"));
    }

    @FXML
    public void handleSubmit() {
        lblError.setVisible(false);
        lblSuccess.setVisible(false);

        if (txtFlightNumber.getText().isEmpty() || txtPlaneId.getText().isEmpty() ||
                txtDepartureAirport.getText().isEmpty() || txtArrivalAirport.getText().isEmpty() ||
                txtDepartureTime.getText().isEmpty() || txtArrivalTime.getText().isEmpty() ||
                txtDuration.getText().isEmpty() || txtStatus.getText().isEmpty()) {

            lblError.setText(LanguageManager.getInstance().getResourceBundle().getString("error.fill_fields"));
            lblError.setVisible(true);
            return;
        }

        if (!isValidDateTime(txtDepartureTime.getText()) || !isValidDateTime(txtArrivalTime.getText())) {
            lblError.setText(LanguageManager.getInstance().getResourceBundle().getString("error.invalid_datetime"));
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
                    txtDepartureTime.getText(),
                    txtArrivalTime.getText(),
                    txtDuration.getText(),
                    txtStatus.getText(),
                    txtStatus.getText()
            );

            flightService.createFlight(flight);
            lblSuccess.setText(LanguageManager.getInstance().getResourceBundle().getString("flight.add.success"));
            lblSuccess.setVisible(true);
            clearForm();
        } catch (NumberFormatException e) {
            lblError.setText(LanguageManager.getInstance().getResourceBundle().getString("error.numbers_only"));
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
