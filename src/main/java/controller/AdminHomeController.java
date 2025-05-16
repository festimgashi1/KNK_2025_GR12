package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import repository.StatisticsRepository;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {

    @FXML private PieChart reservationPieChart;
    @FXML private BarChart<String, Number> flightsBarChart;

    private final StatisticsRepository statisticsRepo = new StatisticsRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadReservationStats();
        loadFlightStats();
    }

    private void loadReservationStats() {
        Map<String, Integer> data = statisticsRepo.getTicketsByPaymentMethod();

        reservationPieChart.getData().clear();
        data.forEach((method, count) ->
                reservationPieChart.getData().add(new PieChart.Data(method, count))
        );
    }

    private void loadFlightStats() {
        Map<String, Integer> data = statisticsRepo.getFlightsPerAirline();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        data.forEach((airline, count) ->
                series.getData().add(new XYChart.Data<>(airline, count))
        );

        flightsBarChart.getData().clear();
        flightsBarChart.getData().add(series);
    }
}
