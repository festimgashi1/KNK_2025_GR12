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
import session.AirlineSession;

import java.io.IOException;

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

    private final FlightService flightService = new FlightService();
    private final ObservableList<Flights> flightsData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
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
        colActions.setCellFactory(col -> new TableCell<Flights, Void>() {
            private final Button btnEdit = new Button("✎");
            private final Button btnDelete = new Button("🗑");

            {
                btnEdit.setStyle("""
                -fx-background-color: #3498db;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-padding: 4 10;
                -fx-cursor: hand;
            """);

                btnDelete.setStyle("""
                -fx-background-color: #e74c3c;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-padding: 4 10;
                -fx-cursor: hand;
            """);

                btnEdit.setOnAction(e -> handleEdit(getTableView().getItems().get(getIndex())));
                btnDelete.setOnAction(e -> handleDelete(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(8, btnEdit, btnDelete);
                    box.setStyle("-fx-alignment: center;");
                    setGraphic(box);
                }
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
            stage.showAndWait(); // wait until edit window closes

            // rifresko listen pas editimit
            loadFlights();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete(Flights flight) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Are you sure you want to delete this flight?");

        ButtonType confirmBtn = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(confirmBtn, cancelBtn);

        alert.showAndWait().ifPresent(response -> {
            if (response == confirmBtn) {
                boolean success = flightService.deleteFlightById(flight.getFlightNumber());
                if (success) {
                    flightsData.remove(flight);
                    lblMessage.setText("Fluturimi u fshi me sukses.");
                    lblMessage.setStyle("-fx-text-fill: green;");
                } else {
                    lblMessage.setText("Fshirja deshtoi. Provo përsëri.");
                    lblMessage.setStyle("-fx-text-fill: red;");
                }
                lblMessage.setVisible(true);
            }
        });
    }
}
