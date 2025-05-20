package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Admin;
import model.Airline;
import model.Costumer;
import services.LanguageManager;
import services.LoginService;
import services.SceneManager;
import session.AdminSession;
import session.CustomerSession;
import session.AirlineSession;

import java.util.ResourceBundle;

public class LogInController {

    @FXML private Label errorLabel;
    @FXML private TextField emailTxt;
    @FXML private PasswordField pwId;
    @FXML private ImageView bgImageView;
    @FXML private StackPane rootPane;

    @FXML private Label welcomeLabel;
    @FXML private Label forgotPasswordLabel;
    @FXML private Label guestLabel;
    @FXML private Hyperlink guestLink;
    @FXML private Button loginButton;
    @FXML private Button signUpButton;
    @FXML private Label noAccountLabel;

    private final LoginService loginService = new LoginService();

    @FXML
    public void initialize() {
        bgImageView.fitWidthProperty().bind(rootPane.widthProperty());
        bgImageView.fitHeightProperty().bind(rootPane.heightProperty());

        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        welcomeLabel.setText(bundle.getString("login.title"));
        emailTxt.setPromptText(bundle.getString("login.email"));
        pwId.setPromptText(bundle.getString("login.password"));
        forgotPasswordLabel.setText(bundle.getString("forgot.password"));
        loginButton.setText(bundle.getString("login.button"));
        signUpButton.setText(bundle.getString("signup.button"));
        guestLabel.setText(bundle.getString("guest.label"));
        guestLink.setText(bundle.getString("guest.button"));
        noAccountLabel.setText(bundle.getString("no.account"));
    }

    @FXML
    public void handleLogInButton(ActionEvent event) {
        String email = emailTxt.getText();
        String password = pwId.getText();

        try {
            Object user = this.loginService.login(email, password);

            if (user instanceof Admin) {
                AdminSession.getInstance().setCurrentAdmin((Admin) user);
                errorLabel.setText("");
                SceneManager.getInstance().switchScene("/Views/admin_home.fxml");
            } else if (user instanceof Airline) {
                Airline airline = (Airline) user;
                AirlineSession.setAirlineId(airline.getAirlineid());
                AirlineSession.setAirlineName(airline.getAirlinename());
                errorLabel.setText("");
                SceneManager.getInstance().switchScene("/Views/add_flight.fxml");
            } else if (user instanceof Costumer) {
                CustomerSession.getInstance().setCurrentCostumer((Costumer) user);
                errorLabel.setText("");
                SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
            } else {
                errorLabel.setText(LanguageManager.getInstance().getResourceBundle().getString("error.unknown_user"));
            }

            cleanFields();

        } catch (Exception e) {
            errorLabel.setText(LanguageManager.getInstance().getResourceBundle().getString("error.invalid_credentials"));
        }
    }

    private void cleanFields() {
        emailTxt.setText("");
        pwId.setText("");
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
