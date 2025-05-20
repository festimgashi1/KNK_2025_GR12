package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import services.AirlineStatisticsService;
import session.AirlineSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class AirlineStatisticsController {

    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML
    private Label lblTotalFlights, lblTotalReservations, lblTotalPassengers;
    @FXML
    private DatePicker dpStart, dpEnd;
    @FXML
    private PieChart reservationPieChart;
    @FXML
    private BarChart<String, Number> flightsBarChart;

    private final AirlineStatisticsService statisticsService = new AirlineStatisticsService();

    @FXML private BarChart<String, Number> reservationsBarChart;

    @FXML
    public void initialize() {
        loadData(null, null);
    }

    @FXML
    private void handleFilter(ActionEvent event) {
        LocalDate start = dpStart.getValue();
        LocalDate end = dpEnd.getValue();
        loadData(start, end);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        dpStart.setValue(null);
        dpEnd.setValue(null);
        loadData(null, null);
    }


    private void loadData(LocalDate start, LocalDate end) {
        int airlineId = AirlineSession.getAirlineId();

        int totalFlights = statisticsService.getTotalFlights(airlineId, start, end);
        lblTotalFlights.setText(String.valueOf(totalFlights));

        int totalReservations = statisticsService.getTotalReservations(airlineId, start, end);
        lblTotalReservations.setText(String.valueOf(totalReservations));

        reservationsBarChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Reservations per Flight");

        Map<String, Integer> reservationsPerFlight = statisticsService.getReservationsGroupedByFlight(airlineId, start, end);
        for (Map.Entry<String, Integer> entry : reservationsPerFlight.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        reservationsBarChart.getData().add(series);
        for (XYChart.Data<String, Number> data : series.getData()) {
            data.getNode().setStyle("-fx-bar-fill: #001f3f;");
        }

        Platform.runLater(() -> {
            for (Node node : reservationsBarChart.lookupAll(".chart-legend-item-symbol")) {
                node.setStyle("-fx-background-color: #001f3f; -fx-background-radius: 3px;");
            }
        });
    }
}
