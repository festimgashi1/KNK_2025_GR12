package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.BoardingPass;
import model.TravelDocuments;
import repository.BoardingPassRepository;
import repository.TravelDocumentsRepository;
import session.CustomerSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CheckInController {

    @FXML private TextField txtFromCode;
    @FXML private TextField txtToCode;
    @FXML private TextField txtName;
    @FXML private TextField txtSeat;
    @FXML private TextField txtGate;

    @FXML private RadioButton rbVisa;
    @FXML private RadioButton rbMasterCard;
    @FXML private RadioButton rbPaypal;
    private ToggleGroup paymentGroup;

    @FXML private DatePicker dpFlightDate;
    @FXML private TextField txtBoardingTime;
    @FXML private TextField txtFlightCode;

    @FXML private TextField txtDocumentId;
    @FXML private TextField txtClientId;
    @FXML private TextField txtBookingId;
    @FXML private DatePicker dpIssueDate;
    @FXML private DatePicker dpExpiryDate;

    @FXML private Button btnConfirmCheckIn;

    private final BoardingPassRepository boardingPassRepo = new BoardingPassRepository();
    private final TravelDocumentsRepository travelDocumentsRepo = new TravelDocumentsRepository();

    @FXML
    public void initialize() {
        paymentGroup = new ToggleGroup();
        rbVisa.setToggleGroup(paymentGroup);
        rbMasterCard.setToggleGroup(paymentGroup);
        rbPaypal.setToggleGroup(paymentGroup);

        // Autopopulate static values (normally passed from session/ticket)
        txtSeat.setText(generateSeat());
        txtGate.setText(generateGate());
        txtClientId.setText(String.valueOf(CustomerSession.getInstance().getCurrentCostumer().getCostumerId()));
    }

    @FXML
    private void handleConfirmCheckIn(MouseEvent event) {
        try {
            String name = txtName.getText();
            String from = txtFromCode.getText();
            String to = txtToCode.getText();
            String seat = txtSeat.getText();
            String gate = txtGate.getText();
            LocalDate flightDate = dpFlightDate.getValue();
            String boardingTime = txtBoardingTime.getText();
            String flightCode = txtFlightCode.getText();

            RadioButton selectedPayment = (RadioButton) paymentGroup.getSelectedToggle();
            String paymentMethod = selectedPayment != null ? selectedPayment.getText() : "";

            BoardingPass pass = new BoardingPass(name, from, to, flightDate, flightCode, gate, boardingTime, seat);
            boardingPassRepo.save(pass);

            int clientId = Integer.parseInt(txtClientId.getText());
            int bookingId = Integer.parseInt(txtBookingId.getText());
            LocalDate issueDate = dpIssueDate.getValue();
            LocalDate expiryDate = dpExpiryDate.getValue();

            TravelDocuments doc = new TravelDocuments(0, clientId, bookingId, java.sql.Date.valueOf(issueDate), java.sql.Date.valueOf(expiryDate), null, null);
            travelDocumentsRepo.save(doc);

            showSuccess("Check-In and Boarding Pass generated successfully!");
        } catch (Exception e) {
            showError("Error during check-in: " + e.getMessage());
        }
    }

    private String generateSeat() {
        return "A" + (new Random().nextInt(30) + 1);
    }

    private String generateGate() {
        return "G" + (new Random().nextInt(10) + 1);
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
