package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.SceneManager;
<<<<<<< Updated upstream
import session.CustomerSession;
=======
>>>>>>> Stashed changes

public class CustomerNavbarController {

    @FXML
    public void showFlights(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/flights.fxml");
    }

    @FXML
    public void showReservation(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/reservations.fxml");
    }

    @FXML
    public void showFeedback(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/feedback.fxml");
    }

    @FXML
    public void goLogIn(ActionEvent event) {
<<<<<<< Updated upstream
        if (CustomerSession.getInstance().getCurrentCostumer() != null) {
            SceneManager.getInstance().switchScene("/Views/costumer_profile.fxml");
        } else {
            SceneManager.getInstance().switchScene("/Views/login.fxml");
        }
    }
    @FXML
    public void handleSignOut(ActionEvent event) {
        session.CustomerSession.getInstance().clear();  // remove session
        SceneManager.getInstance().switchScene("/Views/login.fxml");  // go to login
    }

=======
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }
>>>>>>> Stashed changes
}
