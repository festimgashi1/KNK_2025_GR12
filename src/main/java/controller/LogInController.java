package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Admin;
import model.Airline;
import model.Costumer;
import services.LoginService;
import services.SceneManager;
import session.AdminSession;
import session.CustomerSession;
import session.AirlineSession;

public class LogInController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField pwId;

    private final LoginService loginService;

    public LogInController() {
        this.loginService = new LoginService();
    }

    @FXML
    public void handleLogInButton(ActionEvent event) {
        String email = emailTxt.getText();
        String password = pwId.getText();

        try {
            Object user = this.loginService.login(email, password);

            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                AdminSession.getInstance().setCurrentAdmin(admin);
                errorLabel.setText("");
                SceneManager.getInstance().switchScene("/Views/admin_home.fxml");

            } else if (user instanceof Airline) {
                Airline airline = (Airline) user;
                AirlineSession.setAirlineId(airline.getAirlineid());
                AirlineSession.setAirlineName(((Airline) user).getAirlinename());
                errorLabel.setText("");
                SceneManager.getInstance().switchScene("/Views/add_flight.fxml");

            } else if (user instanceof Costumer) {
                Costumer costumer = (Costumer) user;
                CustomerSession.getInstance().setCurrentCustomer(costumer);
                errorLabel.setText("");
                SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");

            } else {
                errorLabel.setText("Unknown user type.");
            }

            cleanFields();

        } catch (Exception e) {
            errorLabel.setText("Email or password is incorrect.");
        }
    }

    private void cleanFields() {
        this.emailTxt.setText("");
        this.pwId.setText("");
    }

    @FXML
    public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }

    @FXML
    public void goSignUp(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/signup.fxml");
    }

    @FXML
    public void handleGuest(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
    }
}
