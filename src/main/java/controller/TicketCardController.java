package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Tickets;
import model.dto.CreateBookingDto;
import services.SceneManager;
import session.CustomerSession;
import services.BookingService;
import session.TicketSession;
import repository.SeatRepository;

import java.time.LocalDate;
import java.time.ZoneId;

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
        System.out.println("BUY CLICKED – ticket = " + ticket);


        if (CustomerSession.getInstance().getCurrentCostumer() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication Required");
            alert.setHeaderText("You must be logged in to purchase a ticket.");
            alert.setContentText("Please log in to continue.");

            ButtonType loginButton = new ButtonType("Go to Login", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(loginButton, ButtonType.CANCEL);

            alert.showAndWait().ifPresent(response -> {
                if (response == loginButton) {
                    SceneManager.getInstance().switchScene("/Views/login.fxml");
                }
            });
            return;
        }

        if (ticket == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No ticket selected.");
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
            showAlert(Alert.AlertType.ERROR, "Failure", "Reservation failed. Please try again.");
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
