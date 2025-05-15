package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Airline;
import model.PendingAirline;
import repository.ApprovedAirlinesRepository;
import repository.PendingAirlineRepository;
import repository.AirlineRepository;
import services.SceneManager;

import java.util.List;

public class AdminAirlinesRequestController {

    @FXML
    private TableView<PendingAirline> pendingAirlinesTable;
    @FXML
    private TableColumn<PendingAirline, String> colAirlineName, colCountry, colEmail, colPhoneNumber;

    @FXML
    private TableView<Airline> registeredAirlinesTable;
    @FXML
    private TableColumn<Airline, String> colRegisteredName, colRegisteredCountry, colRegisteredEmail, colRegisteredPhone;

    private final PendingAirlineRepository pendingAirlineRepository = new PendingAirlineRepository();
    private final AirlineRepository airlineRepository = new AirlineRepository();
    private final ApprovedAirlinesRepository approvedAirlines = new ApprovedAirlinesRepository();

    @FXML
    public void initialize() {
        // Pending columns
        colAirlineName.setCellValueFactory(cellData -> cellData.getValue().airlineNameProperty());
        colCountry.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        addActionButtonsToPendingTable();
        loadPendingAirlines();

        // Registered columns
        colRegisteredName.setCellValueFactory(new PropertyValueFactory<>("airlinename"));
        colRegisteredCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colRegisteredEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRegisteredPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadApprovedAirlines();
    }

    private void addActionButtonsToPendingTable() {
        TableColumn<PendingAirline, Void> actionsCol = new TableColumn<>("Actions");

        Callback<TableColumn<PendingAirline, Void>, TableCell<PendingAirline, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btnApprove = new Button("Approve");
            private final Button btnDeny = new Button("Deny");

            {
                btnApprove.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
                btnDeny.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

                btnApprove.setOnAction(event -> {
                    PendingAirline airline = getTableView().getItems().get(getIndex());
                    handleApprove(airline);
                });

                btnDeny.setOnAction(event -> {
                    PendingAirline airline = getTableView().getItems().get(getIndex());
                    handleDeny(airline);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(5, btnApprove, btnDeny);
                    setGraphic(hBox);
                }
            }
        };

        actionsCol.setCellFactory(cellFactory);
        pendingAirlinesTable.getColumns().add(actionsCol);
    }

    private void handleApprove(PendingAirline selectedAirline) {
        if (selectedAirline != null) {
            boolean success = airlineRepository.createFromPending(selectedAirline);
            if (success && pendingAirlineRepository.updateStatusToApproved(selectedAirline.getId())) {
                pendingAirlineRepository.deleteByEmail(selectedAirline.getEmail());
                loadPendingAirlines();
                loadApprovedAirlines();
                showAlert("Approved", "Airline approved successfully.");
            } else {
                showAlert("Error", "Approval failed.");
            }
        }
    }

    private void handleDeny(PendingAirline selectedAirline) {
        if (selectedAirline != null) {
            if (pendingAirlineRepository.updateStatusToDenied(selectedAirline.getId()) &&
                    pendingAirlineRepository.deleteByEmail(selectedAirline.getEmail())) {
                loadPendingAirlines();
                showAlert("Denied", "Airline denied successfully.");
            } else {
                showAlert("Error", "Denial failed.");
            }
        }
    }

    @FXML
    public void handleDelete() {
        Airline selected = registeredAirlinesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (airlineRepository.deleteById(selected.getAirlineid())) {
                loadApprovedAirlines();
                showAlert("Deleted", "Airline deleted successfully.");
            } else {
                showAlert("Error", "Failed to delete airline.");
            }
        } else {
            showAlert("No Selection", "Please select an airline to delete.");
        }
    }

    private void loadPendingAirlines() {
        pendingAirlinesTable.setItems(FXCollections.observableArrayList(pendingAirlineRepository.getAll()));
    }

    private void loadApprovedAirlines() {
        registeredAirlinesTable.setItems(FXCollections.observableArrayList(approvedAirlines.getAllApproved()));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }

    @FXML
    public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }
}
