package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import services.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {

    @FXML
    private PieChart reservationPieChart;

    @FXML
    private BarChart flightsBarChart;

    @FXML
    public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/log" +
                "in.fxml");
    }

//    @FXML
//    public void goHome(ActionEvent event) {
//        SceneManager.getInstance().switchScene("/Views/admin_home.fxml");
//    }
//
//    @FXML
//    public void goFlights(ActionEvent event) {
//        SceneManager.getInstance().switchScene("/Views/customer_flights.fxml");
//    }


    public void initialize(URL location, ResourceBundle resources) {
        reservationPieChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Confirmed", 100),
                new PieChart.Data("Pending", 50),
                new PieChart.Data("Cancelled", 20)
        ));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2025");
        series.getData().add(new XYChart.Data<>("Airline A", 80));
        series.getData().add(new XYChart.Data<>("Airline B", 45));
        series.getData().add(new XYChart.Data<>("Airline C", 60));

        flightsBarChart.getData().add(series);
    }
}
