package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Admin;
import model.Airline;
import model.Costumer;
import services.LoginService;
import services.SceneManager;

public class LogInController {

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField pwId;

    private LoginService loginService;

    public LogInController() {
        this.loginService = new LoginService();
    }

    @FXML
    public void handleLogInButton(ActionEvent event) {
        String email = emailTxt.getText();
        String password = pwId.getText();

        try {
            Object user = this.loginService.login(email, password);
            this.cleanFields();

            if (user instanceof Admin) {
                System.out.println("Welcome admin: " + ((Admin) user).getFirstName());
                SceneManager.getInstance().switchScene("/Views/admin_home.fxml");

            } else if (user instanceof Airline) {
                System.out.println("Welcome airline: " + ((Airline) user).getAirlinename());
                SceneManager.getInstance().switchScene("/Views/add_flight.fxml");

            } else if (user instanceof Costumer) {
                System.out.println("Welcome customer: " + ((Costumer) user).getFirstName());
                SceneManager.getInstance().switchScene("/Views/client_interface.fxml");

            } else {
                System.out.println("Unknown user type.");
            }

        } catch (Exception e) {
            System.out.println("Error while logging in: " + e.getMessage());
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
        SceneManager.getInstance().switchScene("/Views/client_interface.fxml");
    }
}
