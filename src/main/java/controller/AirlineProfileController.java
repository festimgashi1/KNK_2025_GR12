package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Airline;
import services.AirlineService;
import services.SceneManager;
import session.AirlineSession;

public class AirlineProfileController {

    @FXML private Label lblAirlineName;
    @FXML private Label lblEmail;
    @FXML private Label lblPhone;
    @FXML private Label lblAddress;

    private final AirlineService airlineService = new AirlineService();

    @FXML
    public void initialize() {
        int airlineId = AirlineSession.getAirlineId();

        Airline airline = airlineService.getAirlineById(airlineId);
        if (airline != null) {
            lblAirlineName.setText(airline.getAirlinename());
            lblEmail.setText(airline.getEmail());
            lblPhone.setText(airline.getPhoneNumber());
            lblAddress.setText(airline.getCountry());

            AirlineSession.setAirlineName(airline.getAirlinename());
        }
    }

    @FXML
    private void goToChangePassword() {
        SceneManager.getInstance().switchScene("/Views/change_airline_password.fxml");    }
}
