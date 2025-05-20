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
import services.LanguageManager;
import services.SceneManager;

import java.io.IOException;
import java.util.ResourceBundle;

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
    @FXML private Label lblTitle;
    @FXML private Label lblFilter;

    @FXML private Button btnAddStaff;

    private final AirportStaffService staffService = new AirportStaffService();
    private ObservableList<AirportStaff> staffList;

    @FXML
    public void initialize() {
        LanguageManager.getInstance().addListener(this::applyTranslations);
        applyTranslations();

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

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblTitle.setText(bundle.getString("staff.title"));
        lblFilter.setText(bundle.getString("staff.filter"));
        btnAddStaff.setText(bundle.getString("staff.add"));

        colId.setText(bundle.getString("staff.id"));
        colFirstName.setText(bundle.getString("staff.firstname"));
        colLastName.setText(bundle.getString("staff.lastname"));
        colPhone.setText(bundle.getString("staff.phone"));
        colRole.setText(bundle.getString("staff.role"));
        colAddress.setText(bundle.getString("staff.address"));
        colShift.setText(bundle.getString("staff.shift"));
        colStartedAt.setText(bundle.getString("staff.start"));
        colDelete.setText(bundle.getString("staff.delete"));
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
            private final Button btn = new Button();

            {
                btn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 6;");
                btn.setOnAction(e -> {
                    AirportStaff staff = getTableView().getItems().get(getIndex());

                    ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle(bundle.getString("staff.confirm.title"));
                    confirmation.setHeaderText(null);
                    confirmation.setContentText(bundle.getString("staff.confirm.message") + "\n\n" +
                            staff.getFirstName() + " " + staff.getLastName());

                    ButtonType yesBtn = new ButtonType(bundle.getString("yes"), ButtonBar.ButtonData.YES);
                    ButtonType noBtn = new ButtonType(bundle.getString("no"), ButtonBar.ButtonData.NO);
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
                btn.setText(LanguageManager.getInstance().getResourceBundle().getString("staff.delete"));
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
            dialog.setTitle(LanguageManager.getInstance().getResourceBundle().getString("staff.add"));
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
