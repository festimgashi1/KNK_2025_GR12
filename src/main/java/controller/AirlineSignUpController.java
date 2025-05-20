package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.dto.PendingAirlineDto;
import services.LanguageManager;
import services.SceneManager;
import services.SignupService;

import java.util.ResourceBundle;

public class AirlineSignUpController {

    @FXML private TextField txtFirstName, txtEmail, txtPhoneNumber, txtAddress;
    @FXML private PasswordField pwdPassword, pwdConfirmPass;
    @FXML private Label errorLabel, waitingLabel;
    @FXML private Button btnSignUp, btnAirline, btnCostumer;
    @FXML private Hyperlink linkLogIn;
    @FXML private Label lblAlreadyAccount, lblTitle;

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

        lblTitle.setText(bundle.getString("airline.signup.title"));
        txtFirstName.setPromptText(bundle.getString("airline.name"));
        txtEmail.setPromptText(bundle.getString("email"));
        txtPhoneNumber.setPromptText(bundle.getString("phone"));
        txtAddress.setPromptText(bundle.getString("address"));
        pwdPassword.setPromptText(bundle.getString("password"));
        pwdConfirmPass.setPromptText(bundle.getString("confirm.password"));
        btnAirline.setText(bundle.getString("airline"));
        btnCostumer.setText(bundle.getString("costumer"));
        linkLogIn.setText(bundle.getString("login.link"));
        lblAlreadyAccount.setText(bundle.getString("already.account"));
        btnSignUp.setText(bundle.getString("signup.button"));
    }

    @FXML
    public void handleSignUpButton(ActionEvent event) {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        errorLabel.setText("");
        waitingLabel.setText("");

        if (txtFirstName.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txtPhoneNumber.getText().isEmpty() || txtAddress.getText().isEmpty() ||
                pwdPassword.getText().isEmpty() || pwdConfirmPass.getText().isEmpty()) {
            errorLabel.setText(bundle.getString("error.fill.fields"));
            return;
        }

        if (!pwdPassword.getText().equals(pwdConfirmPass.getText())) {
            errorLabel.setText(bundle.getString("error.password.mismatch"));
            return;
        }

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
            waitingLabel.setText(bundle.getString("signup.waiting.approval"));

        } catch (Exception e) {
            errorLabel.setText(bundle.getString("signup.failed") + ": " + e.getMessage());
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

    @FXML public void goHome(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
    }
}
