package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import services.CustomerService;
import services.LanguageManager;
import services.SceneManager;

import java.util.ResourceBundle;

public class ChangeCustomerPasswordController {

    @FXML private PasswordField currentPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmPassword;

    @FXML private Label statusLabel;
    @FXML private Label lblTitle;

    @FXML private Button btnSubmit;
    @FXML private Button btnBack;

    private final CustomerService customerService = new CustomerService();

    @FXML
    public void initialize() {
        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        lblTitle.setText(bundle.getString("change.password"));
        currentPassword.setPromptText(bundle.getString("current.password"));
        newPassword.setPromptText(bundle.getString("new.password"));
        confirmPassword.setPromptText(bundle.getString("confirm.password"));
        btnSubmit.setText(bundle.getString("submit"));
        btnBack.setText(bundle.getString("go.back"));
    }

    @FXML
    public void handleChangePassword() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        String current = currentPassword.getText();
        String newPass = newPassword.getText();
        String confirm = confirmPassword.getText();

        if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            statusLabel.setText(bundle.getString("all.fields.required"));
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!newPass.equals(confirm)) {
            statusLabel.setText(bundle.getString("passwords.not.match"));
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean result = customerService.changePassword(current, newPass);
        if (result) {
            statusLabel.setText(bundle.getString("password.success"));
            statusLabel.setStyle("-fx-text-fill: green;");

            currentPassword.clear();
            newPassword.clear();
            confirmPassword.clear();
        } else {
            statusLabel.setText(bundle.getString("incorrect.password"));
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void goBackToProfile() {
        SceneManager.getInstance().switchScene("/Views/costumer_profile.fxml");
    }
}
