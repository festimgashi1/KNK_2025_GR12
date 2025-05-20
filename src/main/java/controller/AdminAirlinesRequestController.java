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
import services.LanguageManager;
import services.SceneManager;

import java.util.List;
import java.util.ResourceBundle;

public class AdminAirlinesRequestController {

    @FXML private Label statusLabel;
    @FXML private TableView<PendingAirline> pendingAirlinesTable;
    @FXML private TableColumn<PendingAirline, String> colAirlineName, colCountry, colEmail, colPhoneNumber;

    @FXML private TableView<Airline> registeredAirlinesTable;
    @FXML private TableColumn<Airline, String> colRegisteredName, colRegisteredCountry, colRegisteredEmail, colRegisteredPhone;
    @FXML private Label lblPendingTitle;
    @FXML private Label lblRegisteredTitle;
    @FXML private Button btnDelete;

    private final PendingAirlineRepository pendingAirlineRepository = new PendingAirlineRepository();
    private final AirlineRepository airlineRepository = new AirlineRepository();
    private final ApprovedAirlinesRepository approvedAirlines = new ApprovedAirlinesRepository();

    private String approveText, denyText, deleteSuccess, deleteFail, deleteSelect, approveSuccess, approveFail, denySuccess, denyFail;

    @FXML
    public void initialize() {
        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();

        colAirlineName.setCellValueFactory(cellData -> cellData.getValue().airlineNameProperty());
        colCountry.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        addActionButtonsToPendingTable();
        loadPendingAirlines();

        colRegisteredName.setCellValueFactory(new PropertyValueFactory<>("airlinename"));
        colRegisteredCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colRegisteredEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRegisteredPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadApprovedAirlines();
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        colAirlineName.setText(bundle.getString("airline.name"));
        colCountry.setText(bundle.getString("address"));
        colEmail.setText(bundle.getString("email"));
        colPhoneNumber.setText(bundle.getString("phone"));
        colRegisteredName.setText(bundle.getString("airline.name"));
        colRegisteredCountry.setText(bundle.getString("address"));
        colRegisteredEmail.setText(bundle.getString("email"));
        colRegisteredPhone.setText(bundle.getString("phone"));

        lblPendingTitle.setText("✈ " + bundle.getString("admin.pending.airlines"));
        lblRegisteredTitle.setText("✔ " + bundle.getString("admin.registered.airlines"));
        btnDelete.setText("🗑 " + bundle.getString("admin.delete.airline"));

        approveText = bundle.getString("approve");
        denyText = bundle.getString("deny");
        deleteSuccess = bundle.getString("airline.delete.success");
        deleteFail = bundle.getString("airline.delete.fail");
        deleteSelect = bundle.getString("airline.delete.select");

        approveSuccess = bundle.getString("airline.approve.success");
        approveFail = bundle.getString("airline.approve.fail");
        denySuccess = bundle.getString("airline.deny.success");
        denyFail = bundle.getString("airline.deny.fail");
    }

    private void addActionButtonsToPendingTable() {
        TableColumn<PendingAirline, Void> actionsCol = new TableColumn<>("Actions");

        Callback<TableColumn<PendingAirline, Void>, TableCell<PendingAirline, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btnApprove = new Button();
            private final Button btnDeny = new Button();

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
                    btnApprove.setText(approveText);
                    btnDeny.setText(denyText);
                    setGraphic(new HBox(5, btnApprove, btnDeny));
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
                setStatus("✅ " + approveSuccess, "#2ecc71");
            } else {
                setStatus("❌ " + approveFail, "#e74c3c");
            }
        }
    }

    private void handleDeny(PendingAirline selectedAirline) {
        if (selectedAirline != null) {
            boolean denied = pendingAirlineRepository.updateStatusToDenied(selectedAirline.getId()) &&
                    pendingAirlineRepository.deleteByEmail(selectedAirline.getEmail());
            if (denied) {
                loadPendingAirlines();
                setStatus("🚫 " + denySuccess, "#f39c12");
            } else {
                setStatus("❌ " + denyFail, "#e74c3c");
            }
        }
    }

    @FXML
    public void handleDelete() {
        Airline selected = registeredAirlinesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (airlineRepository.deleteById(selected.getAirlineid())) {
                loadApprovedAirlines();
                setStatus("🗑 " + deleteSuccess, "#c0392b");
            } else {
                setStatus("❌ " + deleteFail, "#e74c3c");
            }
        } else {
            setStatus("⚠ " + deleteSelect, "#e67e22");
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
