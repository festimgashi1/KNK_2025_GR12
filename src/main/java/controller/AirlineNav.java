package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.SceneManager;

public class AirlineNav {


        @FXML
        public void goToAddFlight(ActionEvent event) {
            SceneManager.getInstance().switchScene("/Views/add_flight.fxml");
        }

        @FXML
        public void goToManageFlights(ActionEvent event) {
            SceneManager.getInstance().switchScene("/Views/manage_flights.fxml");
        }

        public void goToAllFlights(ActionEvent event) {
            SceneManager.getInstance().switchScene("/Views/all_flights.fxml");
        }

        public void goToTickets(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/tickets.fxml");
        }

        @FXML
        public void goToStatistics(ActionEvent event) {
            SceneManager.getInstance().switchScene("/Views/statistics.fxml");
        }

        @FXML
        public void goToProfile(ActionEvent event) {
            SceneManager.getInstance().switchScene("/Views/profile.fxml");
        }

    }

