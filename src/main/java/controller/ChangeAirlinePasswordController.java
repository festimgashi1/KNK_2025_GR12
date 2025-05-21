package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import repository.AirlineRepository;
import session.AirlineSession;
import services.LanguageManager;

import java.util.ResourceBundle;

public class ChangeAirlinePasswordController {

    @FXML private PasswordField txtOldPassword;
    @FXML private PasswordField txtNewPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private Label lblMessage;
    @FXML private Label lblTitle;
    @FXML private Button btnUpdate;

    private final AirlineRepository airlineRepo = new AirlineRepository();

    @FXML
    public void initialize() {
        loadTranslations();
        LanguageManager.getInstance().addListener(this::loadTranslations);
    }

    private void loadTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblTitle.setText("🔒 " + bundle.getString("change.password.header"));
        txtOldPassword.setPromptText(bundle.getString("change.password.current"));
        txtNewPassword.setPromptText(bundle.getString("change.password.new"));
        txtConfirmPassword.setPromptText(bundle.getString("change.password.confirm"));
        btnUpdate.setText(bundle.getString("button.update.password"));
    }

    @FXML
    private void handleChangePassword() {
        int airlineId = AirlineSession.getAirlineId();
        String oldPass = txtOldPassword.getText();
        String newPass = txtNewPassword.getText();
        String confirmPass = txtConfirmPassword.getText();

        if (newPass == null || !newPass.equals(confirmPass)) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText(LanguageManager.getInstance().getResourceBundle().getString("change.password.mismatch"));
            return;
        }

        boolean success = airlineRepo.changePassword(airlineId, oldPass, newPass);
        if (success) {
            lblMessage.setStyle("-fx-text-fill: green;");
            lblMessage.setText(LanguageManager.getInstance().getResourceBundle().getString("change.password.success"));
        } else {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText(LanguageManager.getInstance().getResourceBundle().getString("change.password.incorrect"));
        }
    }
}
