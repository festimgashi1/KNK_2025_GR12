package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Airline;
import model.Flights;
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
    private TableColumn<PendingAirline, String> colAirlineName;

    @FXML
    private TableColumn<PendingAirline, String> colCountry;

    @FXML
    private TableColumn<PendingAirline, String> colEmail;

    @FXML
    private TableColumn<PendingAirline, String> colPhoneNumber;

    @FXML private TableView<Airline> registeredAirlinesTable;
    @FXML private TableColumn<Airline, String> colRegisteredName;
    @FXML private TableColumn<Airline, String> colRegisteredCountry;
    @FXML private TableColumn<Airline, String> colRegisteredEmail;
    @FXML private TableColumn<Airline, String> colRegisteredPhone;


    private final PendingAirlineRepository pendingAirlineRepository = new PendingAirlineRepository();
    private final AirlineRepository airlineRepository = new AirlineRepository();
    private final ApprovedAirlinesRepository approvedAirlines = new ApprovedAirlinesRepository();

    @FXML
    public void initialize() {
        colAirlineName.setCellValueFactory(cellData -> cellData.getValue().airlineNameProperty());
        colCountry.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        loadPendingAirlines();

        colRegisteredName.setCellValueFactory(new PropertyValueFactory<>("airlinename"));
        colRegisteredCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colRegisteredEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRegisteredPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        List<Airline> approved = approvedAirlines.getAllApproved();
        registeredAirlinesTable.setItems(FXCollections.observableArrayList(approved));

        loadApprovedAirlines();
    }

    private void loadPendingAirlines() {
        List<PendingAirline> pendingAirlines = pendingAirlineRepository.getAll();
        pendingAirlinesTable.getItems().setAll(pendingAirlines);
    }

    @FXML
    public void handleApprove() {
        PendingAirline selectedAirline = pendingAirlinesTable.getSelectionModel().getSelectedItem();
        if (selectedAirline != null) {
            boolean success = airlineRepository.createFromPending(selectedAirline);

            if (success) {
                boolean updated = pendingAirlineRepository.updateStatusToApproved(selectedAirline.getId());
                if (updated) {
                    pendingAirlineRepository.deleteByEmail(selectedAirline.getEmail());

                    loadPendingAirlines();
                    loadApprovedAirlines();

                    showAlert("Approval Successful", "The airline has been approved and added to the system.");
                } else {
                    showAlert("Error", "An error occurred while updating the status to approved.");
                }
            } else {
                showAlert("Error", "An error occurred while approving the airline.");
            }
        } else {
            showAlert("No Selection", "Please select a pending airline to approve.");
        }
    }



    @FXML
    public void handleDeny() {
        PendingAirline selectedAirline = pendingAirlinesTable.getSelectionModel().getSelectedItem();
        if (selectedAirline != null) {
            boolean statusUpdated = pendingAirlineRepository.updateStatusToDenied(selectedAirline.getId());

            if (statusUpdated) {
                boolean success = pendingAirlineRepository.deleteByEmail(selectedAirline.getEmail());
                if (success) {
                    loadPendingAirlines();
                    showAlert("Denial Successful", "The airline request has been denied and its status has been updated.");
                } else {
                    showAlert("Error", "An error occurred while deleting the airline from the pending list.");
                }
            } else {
                showAlert("Error", "An error occurred while updating the status to denied.");
            }
        } else {
            showAlert("No Selection", "Please select a pending airline to deny.");
        }
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
    private void loadApprovedAirlines() {
        List<Airline> approved = approvedAirlines.getAllApproved();
        registeredAirlinesTable.setItems(FXCollections.observableArrayList(approved));
    }

    @FXML
    public void handleDelete() {
            Airline selectedAirline = registeredAirlinesTable.getSelectionModel().getSelectedItem();
            if (selectedAirline != null) {
                boolean deleted = airlineRepository.deleteById(selectedAirline.getAirlineid());
                if (deleted) {
                    registeredAirlinesTable.getItems().remove(selectedAirline);
                    showAlert("Airline deleted", "The airline has been successfully removed.");
                } else {
                    showAlert("Error", "Failed to delete airline.");
                }
            } else {
                showAlert("No Selection", "Please select a airline to delete.");
            }
    }


}
