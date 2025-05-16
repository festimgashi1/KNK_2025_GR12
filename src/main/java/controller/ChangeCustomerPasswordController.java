package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import services.CustomerService;
import services.SceneManager;

public class ChangeCustomerPasswordController {

    @FXML private PasswordField currentPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmPassword;
    @FXML private Label statusLabel;

    private final CustomerService customerService = new CustomerService();

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

        boolean result = customerService.changePassword(current, newPass);
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
        SceneManager.getInstance().switchScene("/Views/costumer_profile.fxml");
    }
}
