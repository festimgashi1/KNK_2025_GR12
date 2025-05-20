package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import services.AdminService;
import services.LanguageManager;
import services.SceneManager;

import java.util.ResourceBundle;

public class ChangeAdminPasswordController {

    @FXML private PasswordField currentPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmPassword;
    @FXML private Label statusLabel;
    @FXML private Label titleLabel;

    private final AdminService adminService = new AdminService();

    @FXML
    public void initialize() {
        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        titleLabel.setText(bundle.getString("admin.change_password.title"));
        currentPassword.setPromptText(bundle.getString("admin.change_password.current"));
        newPassword.setPromptText(bundle.getString("admin.change_password.new"));
        confirmPassword.setPromptText(bundle.getString("admin.change_password.confirm"));
    }

    @FXML
    public void handleChangePassword() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        String current = currentPassword.getText();
        String newPass = newPassword.getText();
        String confirm = confirmPassword.getText();

        if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            statusLabel.setText(bundle.getString("admin.change_password.required_fields"));
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!newPass.equals(confirm)) {
            statusLabel.setText(bundle.getString("admin.change_password.no_match"));
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean result = adminService.changePassword(current, newPass);
        if (result) {
            statusLabel.setText(bundle.getString("admin.change_password.success"));
            statusLabel.setStyle("-fx-text-fill: green;");
            currentPassword.clear();
            newPassword.clear();
            confirmPassword.clear();
        } else {
            statusLabel.setText(bundle.getString("admin.change_password.incorrect"));
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void goBackToProfile() {
        SceneManager.getInstance().switchScene("/Views/admin_profile.fxml");
    }
}
