package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.AirportStaff;
import services.AirportStaffService;
import services.LanguageManager;
import services.SceneManager;

import java.util.ResourceBundle;

public class AddAirportStaffController {

    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtRole;
    @FXML private TextField txtAddress;
    @FXML private DatePicker dateStartedAt;
    @FXML private ComboBox<String> comboShift;
    @FXML private Label lblTitle;
    @FXML private Button btnSave;

    private final AirportStaffService service = new AirportStaffService();

    private Runnable onStaffAddedCallback;

    public void setOnStaffAddedCallback(Runnable callback) {
        this.onStaffAddedCallback = callback;
    }

    @FXML
    public void initialize() {
        comboShift.getItems().addAll("AM", "PM"); // ose përkthime nëse ke specifike
        applyTranslations();

        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblTitle.setText(bundle.getString("staff.add"));
        txtFirstName.setPromptText(bundle.getString("staff.firstname"));
        txtLastName.setPromptText(bundle.getString("staff.lastname"));
        txtPhone.setPromptText(bundle.getString("staff.phone"));
        txtRole.setPromptText(bundle.getString("staff.role"));
        txtAddress.setPromptText(bundle.getString("staff.address"));
        dateStartedAt.setPromptText(bundle.getString("staff.start"));
        comboShift.setPromptText(bundle.getString("staff.shift"));
        btnSave.setText(bundle.getString("save"));
    }

    @FXML
    public void onSave() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        try {
            if (txtFirstName.getText().isEmpty() ||
                    txtLastName.getText().isEmpty() ||
                    txtPhone.getText().isEmpty() ||
                    txtRole.getText().isEmpty() ||
                    txtAddress.getText().isEmpty() ||
                    dateStartedAt.getValue() == null ||
                    comboShift.getValue() == null) {

                new Alert(Alert.AlertType.WARNING, bundle.getString("error.fill_fields")).showAndWait();
                return;
            }

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle(bundle.getString("staff.confirm.title"));
            confirmation.setHeaderText(null);
            confirmation.setContentText(bundle.getString("staff.confirm.message"));

            ButtonType yes = new ButtonType(bundle.getString("yes"), ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType(bundle.getString("no"), ButtonBar.ButtonData.NO);
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
                        new Alert(Alert.AlertType.INFORMATION, bundle.getString("staff.success")).showAndWait();
                        SceneManager.getInstance().switchScene("/Views/airport_staff.fxml");
                    } else {
                        new Alert(Alert.AlertType.ERROR, bundle.getString("staff.error.save")).showAndWait();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, bundle.getString("staff.error.unexpected")).showAndWait();
        }
    }
}
