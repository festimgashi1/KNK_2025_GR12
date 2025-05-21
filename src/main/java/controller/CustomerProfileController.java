package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Costumer;
import services.LanguageManager;
import services.SceneManager;
import session.CustomerSession;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerProfileController implements Initializable {

    @FXML private Label lblFirstName;
    @FXML private Label lblLastName;
    @FXML private Label lblEmail;
    @FXML private Label lblBirthdate;

    @FXML private Label lblFirstNameTitle;
    @FXML private Label lblLastNameTitle;
    @FXML private Label lblEmailTitle;
    @FXML private Label lblBirthdateTitle;
    @FXML private Label lblProfileTitle;

    @FXML private Button btnChangePassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyTranslations();

        LanguageManager.getInstance().addListener(this::applyTranslations);

        Costumer loggedIn = CustomerSession.getInstance().getCurrentCostumer();
        if (loggedIn != null) {
            lblFirstName.setText(loggedIn.getFirstName());
            lblLastName.setText(loggedIn.getLastName());
            lblEmail.setText(loggedIn.getEmail());
            lblBirthdate.setText(loggedIn.getBirthDate().toString());
        } else {
            System.out.println("No customer is logged in.");
        }
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblProfileTitle.setText("👤 " + bundle.getString("profile.title"));
        lblFirstNameTitle.setText(bundle.getString("first.name"));
        lblLastNameTitle.setText(bundle.getString("last.name"));
        lblEmailTitle.setText(bundle.getString("email.address"));
        lblBirthdateTitle.setText(bundle.getString("birthdate"));
        btnChangePassword.setText(bundle.getString("change.password"));
    }

    @FXML
    public void goToChangePassword() {
        SceneManager.getInstance().switchScene("/Views/change_customer_password.fxml");
    }
}
