package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.AirportStaff;
import services.AirportStaffService;
import services.SceneManager;

public class AddAirportStaffController {

    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtRole;
    @FXML private TextField txtAddress;
    @FXML private DatePicker dateStartedAt;
    @FXML private ComboBox<String> comboShift;

    private final AirportStaffService service = new AirportStaffService();

    private Runnable onStaffAddedCallback;

    public void setOnStaffAddedCallback(Runnable callback) {
        this.onStaffAddedCallback = callback;
    }

    @FXML
    public void onSave() {
        try {
            if (txtFirstName.getText().isEmpty() ||
                    txtLastName.getText().isEmpty() ||
                    txtPhone.getText().isEmpty() ||
                    txtRole.getText().isEmpty() ||
                    txtAddress.getText().isEmpty() ||
                    dateStartedAt.getValue() == null ||
                    comboShift.getValue() == null) {

                new Alert(Alert.AlertType.WARNING, "Please fill in all fields.").showAndWait();
                return;
            }

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Save");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Are you sure you want to add this staff member?");
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
            confirmation.getButtonTypes().setAll(yes, no);

            confirmation.showAndWait().ifPresent(response -> {
                if (response == yes) {
                    AirportStaff staff = new AirportStaff(
                            txtFirstName.getText(),
                            txtLastName.getText(),
                            txtPhone.getText(),
                            txtRole.getText(),
                            txtAddress.getText(),
                            dateStartedAt.getValue(),
                            comboShift.getValue()
                    );

                    boolean success = service.insertStaff(staff);

                    if (success) {
                        new Alert(Alert.AlertType.INFORMATION, "Staff member added successfully.").showAndWait();
                        SceneManager.getInstance().switchScene("/Views/airport_staff.fxml");
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save staff.").showAndWait();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unexpected error occurred.").showAndWait();
        }
    }

    private void clearFields() {
        txtFirstName.clear();
        txtLastName.clear();
        txtPhone.clear();
        txtRole.clear();
        txtAddress.clear();
        dateStartedAt.setValue(null);
        comboShift.setValue(null);
    }
}
