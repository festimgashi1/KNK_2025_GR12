package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Admin;
import services.SceneManager;
import session.AdminSession;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminProfileController implements Initializable {

    @FXML private Label lblFirstName;
    @FXML private Label lblLastName;
    @FXML private Label lblEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Admin admin = AdminSession.getInstance().getCurrentAdmin();
        if (admin != null) {
            lblFirstName.setText(admin.getFirstName());
            lblLastName.setText(admin.getLastName());
            lblEmail.setText(admin.getEmail());
        }
    }
    @FXML
    public void goToChangePassword(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/change_password.fxml");
    }

}
