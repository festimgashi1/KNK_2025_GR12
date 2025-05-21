package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Flights;
import services.FlightService;
import services.LanguageManager;
import session.AirlineSession;

import java.io.IOException;
import java.util.ResourceBundle;

public class ManageFlightsController {

    @FXML private TableView<Flights> flightsTable;
    @FXML private TableColumn<Flights, Integer> colFlightNumber;
    @FXML private TableColumn<Flights, String> colDeparture;
    @FXML private TableColumn<Flights, String> colArrival;
    @FXML private TableColumn<Flights, String> colDepartureTime;
    @FXML private TableColumn<Flights, String> colArrivalTime;
    @FXML private TableColumn<Flights, String> colStatus;
    @FXML private TableColumn<Flights, Void> colActions;

    @FXML private Label lblMessage;
    @FXML private Label lblTitle;

    private final FlightService flightService = new FlightService();
    private final ObservableList<Flights> flightsData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();
        loadFlights();
    }

    private void loadFlights() {
        flightsData.clear();
        int airlineId = AirlineSession.getAirlineId();
        flightsData.addAll(flightService.getFlightsByAirlineId(airlineId));

        colFlightNumber.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colDeparture.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
        colArrival.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        colArrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        addActionButtons();
        flightsTable.setItems(flightsData);
    }

    private void addActionButtons() {
        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button btnEdit = new Button("✎");
            private final Button btnDelete = new Button("🗑");

            {
                btnEdit.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 4 10; -fx-cursor: hand;");
                btnDelete.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 4 10; -fx-cursor: hand;");
                btnEdit.setOnAction(e -> handleEdit(getTableView().getItems().get(getIndex())));
                btnDelete.setOnAction(e -> handleDelete(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : new HBox(8, btnEdit, btnDelete));
            }
        });
    }

    private void handleEdit(Flights flight) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EditFlight.fxml"));
            Parent root = loader.load();

            EditFlightController controller = loader.getController();
            controller.setFlight(flight);

            Stage stage = new Stage();
            stage.setTitle("Edit Flight");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadFlights();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete(Flights flight) {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(bundle.getString("delete.confirm.title"));
        alert.setHeaderText(bundle.getString("delete.confirm.header"));
        alert.setContentText(bundle.getString("delete.confirm.text"));

        ButtonType confirmBtn = new ButtonType(bundle.getString("yes"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType(bundle.getString("cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(confirmBtn, cancelBtn);

        alert.showAndWait().ifPresent(response -> {
            if (response == confirmBtn) {
                boolean success = flightService.deleteFlightById(flight.getFlightNumber());
                if (success) {
                    flightsData.remove(flight);
                    lblMessage.setText(bundle.getString("delete.success"));
                    lblMessage.setStyle("-fx-text-fill: green;");
                } else {
                    lblMessage.setText(bundle.getString("delete.failed"));
                    lblMessage.setStyle("-fx-text-fill: red;");
                }
                lblMessage.setVisible(true);
            }
        });
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        lblTitle.setText(bundle.getString("flight.manage.title"));
        colFlightNumber.setText(bundle.getString("flight.number"));
        colDeparture.setText(bundle.getString("departure.airport"));
        colArrival.setText(bundle.getString("arrival.airport"));
        colDepartureTime.setText(bundle.getString("departure.time"));
        colArrivalTime.setText(bundle.getString("arrival.time"));
        colStatus.setText(bundle.getString("flight.status"));
        colActions.setText(bundle.getString("actions"));
        lblMessage.setText(bundle.getString("flight.select_message"));
    }
}
