package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Costumer;
import model.dto.CreateCostumerDto;
import services.LanguageManager;
import services.SceneManager;
import services.SignupService;
import session.CustomerSession;

import java.util.ResourceBundle;

public class CostumerSignUpController {

    @FXML private TextField txtFirstName, txtLastName, txtEmail, txtPhoneNumber, txtAddress;
    @FXML private PasswordField pwdPassword, pwdConfirmPass;
    @FXML private DatePicker dtBirthDate;
    @FXML private Label errorLabel;
    @FXML private Button btnAirline, btnCostumer, btnSignUp;
    @FXML private Label lblTitle, lblAlreadyAccount;
    @FXML private Hyperlink linkLogIn;
    @FXML private StackPane rootPane;
    @FXML private ImageView bgImageView;

    private final SignupService signupService = new SignupService();

    @FXML
    public void initialize() {
        bgImageView.fitWidthProperty().bind(rootPane.widthProperty());
        bgImageView.fitHeightProperty().bind(rootPane.heightProperty());

        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblTitle.setText(bundle.getString("signup.title"));
        txtFirstName.setPromptText(bundle.getString("signup.firstname"));
        txtLastName.setPromptText(bundle.getString("signup.lastname"));
        txtEmail.setPromptText(bundle.getString("signup.email"));
        txtPhoneNumber.setPromptText(bundle.getString("signup.phone"));
        txtAddress.setPromptText(bundle.getString("signup.address"));
        dtBirthDate.setPromptText(bundle.getString("signup.birthdate"));
        pwdPassword.setPromptText(bundle.getString("signup.password"));
        pwdConfirmPass.setPromptText(bundle.getString("signup.confirmpassword"));
        btnAirline.setText(bundle.getString("signup.airline"));
        btnCostumer.setText(bundle.getString("signup.customer"));
        btnSignUp.setText(bundle.getString("signup.button"));
        lblAlreadyAccount.setText(bundle.getString("already.account"));
        linkLogIn.setText(bundle.getString("login.link"));
    }

    @FXML
    public void handleSignUpButton(ActionEvent event) {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String phone = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String password = pwdPassword.getText();
        String confirmPass = pwdConfirmPass.getText();

        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()
                || address.isEmpty() || password.isEmpty() || confirmPass.isEmpty()
                || dtBirthDate.getValue() == null) {
            errorLabel.setText(bundle.getString("error.fill_fields"));
            return;
        }

        if (!password.equals(confirmPass)) {
            errorLabel.setText(bundle.getString("error.password_mismatch"));
            return;
        }

        try {
            CreateCostumerDto dto = new CreateCostumerDto(
                    firstName, lastName, email, phone, address, dtBirthDate.getValue(), password, confirmPass
            );

            Costumer user = signupService.create(dto);
            CustomerSession.getInstance().setCurrentCostumer(user);
            errorLabel.setText("");
            SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");

        } catch (Exception e) {
            errorLabel.setText(bundle.getString("error.signup_failed") + ": " + e.getMessage());
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

    @FXML public void handleGuest(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
    }
}
