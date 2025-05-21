package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Airline;
import services.AirlineService;
import services.LanguageManager;
import services.SceneManager;
import session.AirlineSession;

import java.util.ResourceBundle;

public class AirlineProfileController {

    @FXML private Label lblHeader;
    @FXML private Label lblTitleAirline;
    @FXML private Label lblTitleEmail;
    @FXML private Label lblTitlePhone;
    @FXML private Label lblTitleAddress;

    @FXML private Label lblAirlineName;
    @FXML private Label lblEmail;
    @FXML private Label lblPhone;
    @FXML private Label lblAddress;

    @FXML private Button btnChangePassword;

    private final AirlineService airlineService = new AirlineService();

    @FXML
    public void initialize() {
        loadTranslations();
        LanguageManager.getInstance().addListener(this::loadTranslations);

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

    private void loadTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblHeader.setText("✈ " + bundle.getString("airline.profile.header"));
        lblTitleAirline.setText(bundle.getString("airline.profile.name"));
        lblTitleEmail.setText(bundle.getString("airline.profile.email"));
        lblTitlePhone.setText(bundle.getString("airline.profile.phone"));
        lblTitleAddress.setText(bundle.getString("airline.profile.address"));
        btnChangePassword.setText(bundle.getString("button.change.password"));
    }

    @FXML
    private void goToChangePassword() {
        SceneManager.getInstance().switchScene("/Views/change_airline_password.fxml");
    }
}
