package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import services.AdminService;
import services.SceneManager;

public class ChangeAdminPasswordController {

    @FXML private PasswordField currentPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmPassword;
    @FXML private Label statusLabel;

    private final AdminService adminService = new AdminService();

    @FXML
    public void handleChangePassword() {
        String current = currentPassword.getText();
        String newPass = newPassword.getText();
        String confirm = confirmPassword.getText();

        if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        if (!newPass.equals(confirm)) {
            statusLabel.setText("Passwords do not match.");
            return;
        }

        boolean result = adminService.changePassword(current, newPass);
        if (result) {
            statusLabel.setText("Password changed successfully.");
            statusLabel.setStyle("-fx-text-fill: green;");

            currentPassword.clear();
            newPassword.clear();
            confirmPassword.clear();
        } else {
            statusLabel.setText("Incorrect current password.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }

    }
    @FXML
    public void goBackToProfile() {
        SceneManager.getInstance().switchScene("/Views/admin_profile.fxml");
    }

}
