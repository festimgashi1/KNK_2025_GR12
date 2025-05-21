package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Tickets;
import model.dto.CreateBookingDto;
import services.LanguageManager;
import services.SceneManager;
import session.CustomerSession;
import services.BookingService;
import session.TicketSession;
import repository.SeatRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class TicketCardController {

    @FXML private TextField txtPrice;
    @FXML private TextField txtFrom;
    @FXML private TextField txtTo;
    @FXML private TextField txtStatus;
    @FXML private TextField txtDepartureTime;
    @FXML private TextField txtArrivalTime;
    @FXML private TextField txtDuration;
    @FXML private TextField txtAirline;
    @FXML private Button btnBuy;

    private Tickets ticket;
    private final BookingService bookingService = new BookingService();

    @FXML
    public void initialize() {
        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        btnBuy.setText(bundle.getString("buy"));

        if (txtFrom.getScene() != null) {
            ((Label) txtFrom.getScene().lookup("#lblFrom")).setText(bundle.getString("from"));
            ((Label) txtTo.getScene().lookup("#lblTo")).setText(bundle.getString("to"));
            ((Label) txtStatus.getScene().lookup("#lblStatus")).setText(bundle.getString("status"));
            ((Label) txtDepartureTime.getScene().lookup("#lblDeparture")).setText(bundle.getString("departure"));
            ((Label) txtArrivalTime.getScene().lookup("#lblArrival")).setText(bundle.getString("arrival"));
            ((Label) txtDuration.getScene().lookup("#lblDuration")).setText(bundle.getString("duration"));
            ((Label) txtAirline.getScene().lookup("#lblAirline")).setText(bundle.getString("airline"));
        }
    }

    public void setTicketData(Tickets ticket) {
        this.ticket = ticket;
        txtPrice.setText(String.valueOf(ticket.getTicketPrice()));
        txtFrom.setText(ticket.getDepartureAirport());
        txtTo.setText(ticket.getArrivalAirport());
        txtStatus.setText(ticket.getStatus());
        txtDepartureTime.setText(ticket.getDepartureTime().toString());
        txtArrivalTime.setText(ticket.getArrivalTime().toString());
        txtDuration.setText(ticket.getDuration());
        txtAirline.setText(ticket.getAirlineName());
    }

    @FXML
    private void handleBuyClick() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        if (CustomerSession.getInstance().getCurrentCostumer() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(bundle.getString("auth.required"));
            alert.setHeaderText(bundle.getString("login.required"));
            alert.setContentText(bundle.getString("login.to.continue"));

            ButtonType loginButton = new ButtonType(bundle.getString("go.to.login"), ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(loginButton, ButtonType.CANCEL);

            alert.showAndWait().ifPresent(response -> {
                if (response == loginButton) {
                    SceneManager.getInstance().switchScene("/Views/login.fxml");
                }
            });
            return;
        }

        if (ticket == null) {
            showAlert(Alert.AlertType.ERROR, bundle.getString("error"), bundle.getString("no.ticket.selected"));
            return;
        }

        LocalDate departureDate = ticket.getDepartureTime()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String seatNumber = generateSeatNumber();

        SeatRepository seatRepo = new SeatRepository();
        seatRepo.insertIfNotExists(seatNumber, ticket.getFlightNumber());

        CreateBookingDto dto = new CreateBookingDto(
                CustomerSession.getInstance().getCurrentCostumer().getCostumerId(),
                ticket.getFlightNumber(),
                departureDate,
                ticket.getArrivalAirport(),
                ticket.getTicketPrice(),
                seatNumber
        );

        boolean success = bookingService.createBooking(dto);
        if (success) {
            TicketSession.getInstance().setSelectedTicket(ticket);
            TicketSession.getInstance().setSeatNumber(seatNumber);
            SceneManager.getInstance().switchScene("/Views/CheckInView.fxml");
        } else {
            showAlert(Alert.AlertType.ERROR, bundle.getString("failure"), bundle.getString("reservation.failed"));
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String generateSeatNumber() {
        int row = (int) (Math.random() * 30) + 1;
        char seat = (char) ('A' + (int) (Math.random() * 6));
        return row + String.valueOf(seat);
    }
}
