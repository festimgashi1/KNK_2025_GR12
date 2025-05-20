package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.Admin;
import services.LanguageManager;
import services.SceneManager;
import session.AdminSession;
import javafx.scene.control.Button;


import java.io.IOException;
import java.util.ResourceBundle;

public class AdminDashboardController {

    @FXML private StackPane contentPane;
    @FXML private Label lblAdminName;
    @FXML private Button btnDashboard, btnAirlines, btnFlights, btnProfile, btnSignOut;

    private Node loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(getClass().getResource(fxml));
    }

    @FXML
    public void initialize() {
        Admin admin = AdminSession.getInstance().getCurrentAdmin();
        if (admin != null) {
            lblAdminName.setText("✈ " + admin.getFirstName());
        } else {
            lblAdminName.setText("✈ Admin");
        }

        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        btnDashboard.setText("📊 " + bundle.getString("admin.dashboard"));
        btnAirlines.setText("🛫 " + bundle.getString("admin.airlines"));
        btnFlights.setText("🗓 " + bundle.getString("admin.flights"));
        btnProfile.setText("👤 " + bundle.getString("admin.profile"));
        btnSignOut.setText("🚪 " + bundle.getString("admin.logout"));
    }

    @FXML
    private void showStatistics() throws IOException {
        SceneManager.getInstance().switchScene("/Views/admin_home.fxml");
    }

    @FXML
    private void showFlights() throws IOException {
        SceneManager.getInstance().switchScene("/Views/admin_flights.fxml");
    }

    @FXML
    private void showAirlines() throws IOException {
        SceneManager.getInstance().switchScene("/Views/admin_airlines.fxml");
    }

    @FXML
    public void goLogIn(ActionEvent event) {
        if (AdminSession.getInstance().getCurrentAdmin() != null) {
            SceneManager.getInstance().switchScene("/Views/admin_profile.fxml");
        } else {
            SceneManager.getInstance().switchScene("/Views/login.fxml");
        }
    }

    @FXML
    public void handleSignOut(ActionEvent event) {
        AdminSession.getInstance().clear();
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }
}
