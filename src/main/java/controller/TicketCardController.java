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

    @FXML private TextField txtPrice, txtFrom, txtTo, txtStatus, txtDepartureTime, txtArrivalTime, txtDuration, txtAirline;
    @FXML private Label lblFrom, lblTo, lblStatus, lblDeparture, lblArrival, lblDuration, lblAirline;
    @FXML private Button btnBuy;
    @FXML private Label lblChoiceTitle;
    @FXML private Label lblChoiceSubtitle;

    private Tickets ticket;
    private final BookingService bookingService = new BookingService();

    @FXML
    public void initialize() {
        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblFrom.setText(bundle.getString("from"));
        lblTo.setText(bundle.getString("to"));
        lblStatus.setText(bundle.getString("status"));
        lblDeparture.setText(bundle.getString("departure"));
        lblArrival.setText(bundle.getString("arrival"));
        lblDuration.setText(bundle.getString("duration"));
        lblAirline.setText(bundle.getString("airline"));
        btnBuy.setText(bundle.getString("buy"));

        lblChoiceTitle.setText(bundle.getString("choice.title"));
        lblChoiceSubtitle.setText(bundle.getString("choice.subtitle"));
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
