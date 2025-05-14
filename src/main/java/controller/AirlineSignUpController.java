package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dto.CreateAirlineDto;
import model.dto.PendingAirlineDto;
import services.SceneManager;
import services.SignupService;

public class AirlineSignUpController {

    @FXML private TextField txtFirstName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhoneNumber;
    @FXML private TextField txtAddress;
    @FXML private PasswordField pwdPassword;
    @FXML private PasswordField pwdConfirmPass;

    private final SignupService signupService = new SignupService();

    @FXML
    public void handleSignUpButton(ActionEvent event) {
        try {
            PendingAirlineDto dto = new PendingAirlineDto(
                    txtFirstName.getText(),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    pwdPassword.getText(),
                    pwdConfirmPass.getText(),
                    txtPhoneNumber.getText()
            );
            signupService.createAirline(dto);
            System.out.println("Waiting for admin's approval...");
            SceneManager.getInstance().switchScene("/Views/pending_airline.fxml");
        } catch (Exception e) {
            System.out.println("Airline signup failed: " + e.getMessage());
        }
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
    public void goAirline(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/airlinesignup.fxml");
    }

    public void goHome(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/client_interface.fxml");
    }
}

