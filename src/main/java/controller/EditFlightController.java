package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Flights;
import services.FlightService;
import services.LanguageManager;

import java.util.ResourceBundle;

public class EditFlightController {

    @FXML private Label lblTitle;
    @FXML private Label lblFlightNumber;
    @FXML private Label lblPlaneId;
    @FXML private Label lblDepartureAirport;
    @FXML private Label lblArrivalAirport;
    @FXML private Label lblDepartureTime;
    @FXML private Label lblArrivalTime;
    @FXML private Label lblDuration;
    @FXML private Label lblStatus;

    @FXML private TextField txtFlightNumber;
    @FXML private TextField txtPlaneId;
    @FXML private TextField txtDepartureAirport;
    @FXML private TextField txtArrivalAirport;
    @FXML private TextField txtDepartureTime;
    @FXML private TextField txtArrivalTime;
    @FXML private TextField txtDuration;
    @FXML private TextField txtStatus;

    @FXML private Button btnSave;

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
    public void initialize() {
        loadTranslations();

        // Register for language change updates
        LanguageManager.getInstance().addListener(this::loadTranslations);
    }

    private void loadTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblTitle.setText(bundle.getString("edit.flight.title"));
        lblFlightNumber.setText(bundle.getString("edit.flight.number"));
        lblPlaneId.setText(bundle.getString("edit.plane.id"));
        lblDepartureAirport.setText(bundle.getString("edit.departure.airport"));
        lblArrivalAirport.setText(bundle.getString("edit.arrival.airport"));
        lblDepartureTime.setText(bundle.getString("edit.departure.time"));
        lblArrivalTime.setText(bundle.getString("edit.arrival.time"));
        lblDuration.setText(bundle.getString("edit.duration"));
        lblStatus.setText(bundle.getString("edit.status"));
        btnSave.setText(bundle.getString("button.save.changes"));
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
