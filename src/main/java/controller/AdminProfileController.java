package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Admin;
import services.LanguageManager;
import services.SceneManager;
import session.AdminSession;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminProfileController implements Initializable {

    @FXML private Label lblFirstName;
    @FXML private Label lblLastName;
    @FXML private Label lblEmail;

    @FXML private Label lblTitle;
    @FXML private Label lblFirstNameTitle;
    @FXML private Label lblLastNameTitle;
    @FXML private Label lblEmailTitle;
    @FXML private Label lblChangePassword;
    @FXML private Button btnChangePassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Admin admin = AdminSession.getInstance().getCurrentAdmin();
        if (admin != null) {
            lblFirstName.setText(admin.getFirstName());
            lblLastName.setText(admin.getLastName());
            lblEmail.setText(admin.getEmail());
        }

        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        lblTitle.setText(bundle.getString("admin.profile.title"));
        lblFirstNameTitle.setText(bundle.getString("admin.profile.firstname"));
        lblLastNameTitle.setText(bundle.getString("admin.profile.lastname"));
        lblEmailTitle.setText(bundle.getString("admin.profile.email"));
        lblChangePassword.setText(bundle.getString("admin.profile.change_password"));
        btnChangePassword.setText(bundle.getString("admin.profile.change_password"));
    }

    @FXML
    public void goToChangePassword(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/change_password.fxml");
    }
}
