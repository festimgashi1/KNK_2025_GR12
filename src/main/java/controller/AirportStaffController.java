package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AirportStaff;
import services.AirportStaffService;
import services.SceneManager;

import java.io.IOException;

public class AirportStaffController {

    @FXML private TableView<AirportStaff> staffTable;
    @FXML private TableColumn<AirportStaff, Integer> colId;
    @FXML private TableColumn<AirportStaff, String> colFirstName;
    @FXML private TableColumn<AirportStaff, String> colLastName;
    @FXML private TableColumn<AirportStaff, Integer> colPhone;
    @FXML private TableColumn<AirportStaff, String> colRole;
    @FXML private TableColumn<AirportStaff, String> colAddress;
    @FXML private TableColumn<AirportStaff, String> colShift;
    @FXML private TableColumn<AirportStaff, String> colStartedAt;
    @FXML private TableColumn<AirportStaff, Void> colDelete;
    @FXML private ComboBox<String> filterCombo;

    private final AirportStaffService staffService = new AirportStaffService();
    private ObservableList<AirportStaff> staffList;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStartedAt.setCellValueFactory(new PropertyValueFactory<>("startedAt"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));

        addDeleteButton();

        filterCombo.setItems(FXCollections.observableArrayList("All", "AM", "PM"));
        filterCombo.setValue("All");
        filterCombo.setOnAction(e -> loadFilteredData());

        loadFilteredData();
    }

    private void loadFilteredData() {
        String shift = filterCombo.getValue();
        staffList = FXCollections.observableArrayList(
                shift.equals("All") ? staffService.getAllStaff() : staffService.getStaffByShift(shift)
        );
        staffTable.setItems(staffList);
    }

    private void addDeleteButton() {
        colDelete.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Delete");
            {
                btn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 6;");
                btn.setOnAction(e -> {
                    AirportStaff staff = getTableView().getItems().get(getIndex());

                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Confirm Deletion");
                    confirmation.setHeaderText(null);
                    confirmation.setContentText("Are you sure you want to delete this staff member?\n\n"
                            + staff.getFirstName() + " " + staff.getLastName());

                    ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);
                    confirmation.getButtonTypes().setAll(yesBtn, noBtn);

                    confirmation.showAndWait().ifPresent(response -> {
                        if (response == yesBtn) {
                            staffService.deleteStaffById(staff.getId());
                            loadFilteredData();
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }


    @FXML
    public void onAddStaff() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/add_airport_staff.fxml"));
            Parent root = loader.load();

            Stage dialog = new Stage();
            dialog.setTitle("Add Staff");
            dialog.setScene(new Scene(root));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            dialog.showAndWait();

            loadFilteredData();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to open Add Staff window").showAndWait();
        }
    }


}
