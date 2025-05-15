package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import model.Costumer;
import model.dto.CreateCostumerDto;
import services.SceneManager;
import services.SignupService;

import java.time.LocalDate;

public class CostumerSignUpController {
    @FXML private TextField txtFirstName, txtLastName, txtEmail, txtPhoneNumber, txtAddress, pwdPassword, pwdConfirmPass;
    @FXML private DatePicker dtBirthDate;

    private final SignupService signupService = new SignupService();

    @FXML
    public void handleSignUpButton(ActionEvent event) {
        try {
            CreateCostumerDto dto = new CreateCostumerDto(
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtEmail.getText(),
                    txtPhoneNumber.getText(),
                    txtAddress.getText(),
                    dtBirthDate.getValue(),
                    pwdPassword.getText(),
                    pwdConfirmPass.getText()
            );
            Costumer user = signupService.create(dto);
            System.out.println("Customer registered: " + user.getFirstName());
            SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
        } catch (Exception e) {
            System.out.println("Signup failed: " + e.getMessage());
        }
    }

    @FXML public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/log_in.fxml");
    }

    @FXML public void goSignUp(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/sign_up.fxml");
    }

    @FXML public void goAirline(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/airline_signup.fxml");
    }

    @FXML
    public void handleGuest(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/client_interface.fxml");
    }
}
