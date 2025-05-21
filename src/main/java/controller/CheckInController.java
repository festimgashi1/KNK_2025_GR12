package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Tickets;
import model.BoardingPass;
import model.TravelDocuments;
import repository.BoardingPassRepository;
import repository.TravelDocumentsRepository;
import services.LanguageManager;
import services.SceneManager;
import session.CustomerSession;
import session.TicketSession;

import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CheckInController {

    @FXML private TextField txtFromCode, txtToCode, txtName, txtSeat, txtGate;
    @FXML private RadioButton rbVisa, rbMasterCard, rbPaypal;
    private ToggleGroup paymentGroup;
    @FXML private DatePicker dpFlightDate;
    @FXML private TextField txtBoardingTime, txtFlightCode;
    @FXML private TextField txtClientId;
    @FXML private DatePicker dpIssueDate, dpExpiryDate;

    @FXML private Label lblPaymentMethod, lblFlightDate, lblBoardingTime, lblFlightCode;
    @FXML private Label lblDocumentDetails, lblError;

    private final BoardingPassRepository boardingPassRepo = new BoardingPassRepository();
    private final TravelDocumentsRepository travelDocumentsRepo = new TravelDocumentsRepository();

    @FXML
    public void initialize() {
        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);

        paymentGroup = new ToggleGroup();
        rbVisa.setToggleGroup(paymentGroup);
        rbMasterCard.setToggleGroup(paymentGroup);
        rbPaypal.setToggleGroup(paymentGroup);

        Tickets ticket = TicketSession.getInstance().getSelectedTicket();
        String seatNumber = TicketSession.getInstance().getSeatNumber();

        if (ticket != null && seatNumber != null) {
            txtBoardingTime.setText(ticket.getDepartureTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().toString());
            txtFromCode.setText(ticket.getDepartureAirport());
            txtToCode.setText(ticket.getArrivalAirport());
            txtFlightCode.setText(String.valueOf(ticket.getFlightNumber()));
            dpFlightDate.setValue(ticket.getDepartureTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            txtSeat.setText(seatNumber);
            txtGate.setText(generateGate());

            txtName.setText(CustomerSession.getInstance().getCurrentCostumer().getFirstName() + " " +
                    CustomerSession.getInstance().getCurrentCostumer().getLastName());
            txtClientId.setText(String.valueOf(CustomerSession.getInstance().getCurrentCostumer().getCostumerId()));
        } else {
            showError(LanguageManager.getInstance().getResourceBundle().getString("checkin.error.missing.ticket"));
        }
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        rbVisa.setText("Visa");
        rbMasterCard.setText("MasterCard");
        rbPaypal.setText("PayPal");

        lblPaymentMethod.setText(bundle.getString("payment.method"));
        lblFlightDate.setText(bundle.getString("flight.date"));
        lblBoardingTime.setText(bundle.getString("boarding.time"));
        lblFlightCode.setText(bundle.getString("flight.code"));
        lblDocumentDetails.setText(bundle.getString("travel.document.details"));
    }

    @FXML
    private void handleConfirmCheckIn(ActionEvent event) {
        try {
            ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

            String name = txtName.getText();
            String from = txtFromCode.getText();
            String to = txtToCode.getText();
            String seat = txtSeat.getText();
            String gate = txtGate.getText();
            LocalDate flightDate = dpFlightDate.getValue();
            String boardingTime = txtBoardingTime.getText();
            String flightCode = txtFlightCode.getText();
            LocalDate issueDate = dpIssueDate.getValue();
            LocalDate expiryDate = dpExpiryDate.getValue();

            if (flightDate == null || issueDate == null || expiryDate == null) {
                showError(bundle.getString("checkin.error.required.dates"));
                return;
            }

            int clientId = Integer.parseInt(txtClientId.getText());

            BoardingPass pass = new BoardingPass(0, name, from, to, flightDate, flightCode, gate, boardingTime, seat);
            boardingPassRepo.save(pass);

            TravelDocuments doc = new TravelDocuments(0, clientId, null, java.sql.Date.valueOf(issueDate), java.sql.Date.valueOf(expiryDate), null, null);
            travelDocumentsRepo.save(doc);

            SceneManager.getInstance().setData("boardingPass", pass);
            SceneManager.getInstance().switchScene("/Views/BoardingPass.fxml");

        } catch (Exception e) {
            e.printStackTrace();
            showError("Check-in error: " + e.getMessage());
        }
    }

    private String generateGate() {
        return "G" + (new Random().nextInt(10) + 1);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
