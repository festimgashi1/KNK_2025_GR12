package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.Admin;
import services.SceneManager;
import session.AdminSession;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private StackPane contentPane;

    private Node loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(getClass().getResource(fxml));
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
    private Label lblAdminName;

    @FXML
    public void initialize() {
        Admin admin = session.AdminSession.getInstance().getCurrentAdmin();
        if (admin != null) {
            lblAdminName.setText("✈ " + admin.getFirstName());
        } else {
            lblAdminName.setText("✈ Admin");
        }
    }

    @FXML
    public void goLogIn(ActionEvent event) {
        if (AdminSession.getInstance().getCurrentAdmin() != null) {
            SceneManager.getInstance().switchScene("/Views/admin_profile.fxml");
        } else {
            SceneManager.getInstance().switchScene("/Views/login.fxml");
        }
    }

    // 🚪 Sign Out
    @FXML
    public void handleSignOut(ActionEvent event) {
        AdminSession.getInstance().clear(); // remove session
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }
}
