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
    private Label statusLabel;

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
                setStatus("✅ Airline approved successfully.", "#2ecc71");
            } else {
                setStatus("❌ Approval failed.", "#e74c3c");
            }
        }
    }

    private void handleDeny(PendingAirline selectedAirline) {
        if (selectedAirline != null) {
            boolean denied = pendingAirlineRepository.updateStatusToDenied(selectedAirline.getId()) &&
                    pendingAirlineRepository.deleteByEmail(selectedAirline.getEmail());
            if (denied) {
                loadPendingAirlines();
                setStatus("🚫 Airline denied and removed.", "#f39c12");
            } else {
                setStatus("❌ Denial failed.", "#e74c3c");
            }
        }
    }

    @FXML
    public void handleDelete() {
        Airline selected = registeredAirlinesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (airlineRepository.deleteById(selected.getAirlineid())) {
                loadApprovedAirlines();
                setStatus("🗑 Airline deleted successfully.", "#c0392b");
            } else {
                setStatus("❌ Failed to delete airline.", "#e74c3c");
            }
        } else {
            setStatus("⚠ Please select an airline to delete.", "#e67e22");
        }
    }

    private void loadPendingAirlines() {
        pendingAirlinesTable.setItems(FXCollections.observableArrayList(pendingAirlineRepository.getAll()));
    }

    private void loadApprovedAirlines() {
        registeredAirlinesTable.setItems(FXCollections.observableArrayList(approvedAirlines.getAllApproved()));
    }

    private void setStatus(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 14px;");
    }

    @FXML
    public void goLogIn(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/login.fxml");
    }
}
