package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import repository.AirlineRepository;
import session.AirlineSession;
import services.PasswordHasher;

public class ChangeAirlinePasswordController {

    @FXML private PasswordField txtOldPassword;
    @FXML private PasswordField txtNewPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private Label lblMessage;

    private final AirlineRepository airlineRepo = new AirlineRepository();

    @FXML
    private void handleChangePassword() {
        int airlineId = AirlineSession.getAirlineId();
        String oldPass = txtOldPassword.getText();
        String newPass = txtNewPassword.getText();
        String confirmPass = txtConfirmPassword.getText();

        if (newPass == null || !newPass.equals(confirmPass)) {
            lblMessage.setText("New passwords do not match.");
            return;
        }

        boolean success = airlineRepo.changePassword(airlineId, oldPass, newPass);
        if (success) {
            lblMessage.setStyle("-fx-text-fill: green;");
            lblMessage.setText("Password changed successfully.");
        } else {
            lblMessage.setText("Old password is incorrect.");
        }
    }
}