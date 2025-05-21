package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import services.AirlineStatisticsService;
import session.AirlineSession;
import services.LanguageManager;

import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class AirlineStatisticsController {

    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    @FXML private Label lblHeader;
    @FXML private Label lblTotalFlightsText;
    @FXML private Label lblTotalReservationsText;
    @FXML private Label lblTotalFlights;
    @FXML private Label lblTotalReservations;

    @FXML private DatePicker dpStart, dpEnd;
    @FXML private Button btnFilter, btnBack;
    @FXML private BarChart<String, Number> reservationsBarChart;

    private final AirlineStatisticsService statisticsService = new AirlineStatisticsService();

    @FXML
    public void initialize() {
        loadTranslations();
        LanguageManager.getInstance().addListener(this::loadTranslations);
        loadData(null, null);
    }

    private void loadTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblHeader.setText("📊 " + bundle.getString("airline.statistics.header"));
        lblTotalFlightsText.setText(bundle.getString("label.total.flights"));
        lblTotalReservationsText.setText(bundle.getString("label.total.reservations"));
        btnFilter.setText(bundle.getString("button.filter"));
        btnBack.setText(bundle.getString("button.back"));
        xAxis.setLabel(bundle.getString("chart.xaxis"));
        yAxis.setLabel(bundle.getString("chart.yaxis"));
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

        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(bundle.getString("chart.series.reservations")); // 🔁 përkthim i legjendës

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
