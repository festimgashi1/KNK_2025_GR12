package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Costumer;
import model.dto.CreateCostumerDto;
import services.SceneManager;
import services.SignupService;
import session.CustomerSession;

public class CostumerSignUpController {

    @FXML private TextField txtFirstName, txtLastName, txtEmail, txtPhoneNumber, txtAddress;
    @FXML private PasswordField pwdPassword, pwdConfirmPass;
    @FXML private DatePicker dtBirthDate;
    @FXML private Label errorLabel;

    private final SignupService signupService = new SignupService();

    @FXML
    public void handleSignUpButton(ActionEvent event) {
        // Marrja e të dhënave
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String phone = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String password = pwdPassword.getText();
        String confirmPass = pwdConfirmPass.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()
                || address.isEmpty() || password.isEmpty() || confirmPass.isEmpty()
                || dtBirthDate.getValue() == null) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPass)) {
            errorLabel.setText("Passwords do not match.");
            return;
        }

        try {
            CreateCostumerDto dto = new CreateCostumerDto(
                    firstName, lastName, email, phone, address, dtBirthDate.getValue(), password, confirmPass
            );

            Costumer user = signupService.create(dto);

            CustomerSession.getInstance().setCurrentCostumer(user);

            System.out.println("Customer registered: " + user.getFirstName());
            errorLabel.setText("");

            SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
        } catch (Exception e) {
            errorLabel.setText("Signup failed: " + e.getMessage());
            System.out.println("Signup failed: " + e.getMessage());
        }
    }

    @FXML public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }

    @FXML public void goSignUp(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/signup.fxml");
    }

    @FXML public void goAirline(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/airlinesignup.fxml");
    }

    @FXML
    public void handleGuest(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
    }
    @FXML private StackPane rootPane;
    @FXML private ImageView bgImageView;

    @FXML
    public void initialize() {
        bgImageView.fitWidthProperty().bind(rootPane.widthProperty());
        bgImageView.fitHeightProperty().bind(rootPane.heightProperty());
    }

}
