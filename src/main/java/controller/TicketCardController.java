package controller;

import javafx.fxml.FXML;
<<<<<<< Updated upstream
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Tickets;
import model.dto.CreateBookingDto;
import services.SceneManager;
import session.CustomerSession;
import services.BookingService;
import model.Tickets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
=======
import javafx.scene.control.*;
import model.Booking;
import model.Tickets;
import services.BookingService;
import session.SessionManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
>>>>>>> Stashed changes

public class TicketCardController {

    @FXML private TextField txtPrice;
    @FXML private TextField txtFrom;
    @FXML private TextField txtTo;
<<<<<<< Updated upstream
    @FXML private TextField txtStatus;
    @FXML private TextField txtDepartureTime;
    @FXML private TextField txtArrivalTime;
    @FXML private TextField txtDuration;
    @FXML private TextField txtAirline;
=======
    @FXML private TextField txtDuration;
    @FXML private TextField txtDepartureTime;
    @FXML private TextField txtArrivalTime;
>>>>>>> Stashed changes
    @FXML private Button btnBuy;

    private Tickets ticket;
    private final BookingService bookingService = new BookingService();

    public void setTicketData(Tickets ticket) {
        this.ticket = ticket;

<<<<<<< Updated upstream
        txtPrice.setText(String.valueOf(ticket.getTicketPrice()));
        txtFrom.setText(ticket.getDepartureAirport());
        txtTo.setText(ticket.getArrivalAirport());
        txtStatus.setText(ticket.getStatus());
        txtDepartureTime.setText(ticket.getDepartureTime().toString());
        txtArrivalTime.setText(ticket.getArrivalTime().toString());
        txtDuration.setText(ticket.getDuration());
        txtAirline.setText(ticket.getAirlineName());
=======
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        txtPrice.setText("€" + ticket.getTicketPrice());
        txtFrom.setText(ticket.getDepartureAirport());
        txtTo.setText(ticket.getArrivalAirport());
        txtDuration.setText(ticket.getDuration());
        txtDepartureTime.setText(timeFormat.format(ticket.getDepartureTime()));
        txtArrivalTime.setText(timeFormat.format(ticket.getArrivalTime()));
>>>>>>> Stashed changes
    }

    @FXML
    private void handleBuyClick() {
<<<<<<< Updated upstream
        if (ticket == null || CustomerSession.getInstance().getCurrentCostumer() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Missing ticket or customer session.");
            return;
        }

        LocalDate departureDate = ticket.getDepartureTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();


        String seatNumber = generateSeatNumber();

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
            showAlert(Alert.AlertType.INFORMATION, "Success", "Reservation completed successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Failure", "Reservation failed. Please try again.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
=======
        try {
            int costumerId = SessionManager.getCostumerId();
            if (costumerId == 0) {
                showAlert(Alert.AlertType.ERROR, "You're not logged in!");
                return;
            }

            // Konverto datën e fluturimit në LocalDate
            LocalDate departureDate = new java.sql.Date(ticket.getDepartureTime().getTime()).toLocalDate();

            Booking booking = new Booking(
                    costumerId,
                    ticket.getFlightNumber(),
                    departureDate,
                    ticket.getArrivalAirport(),
                    ticket.getTicketPrice(),
                    "A1" // përkohësisht, seatNumber fiks
            );

            boolean success = bookingService.saveBooking(booking);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Ticket booked successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Booking failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Unexpected error occurred.");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Booking");
>>>>>>> Stashed changes
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
<<<<<<< Updated upstream

    private String generateSeatNumber() {
        int row = (int) (Math.random() * 30) + 1; // 1-30
        char seat = (char) ('A' + (int) (Math.random() * 6)); // A-F
        return row + String.valueOf(seat);
    }

    @FXML
    public void initialize() {
        Tickets ticket = (Tickets) SceneManager.getInstance().getData("selectedTicket");
        if (ticket != null) {
            setTicketData(ticket);
        }

    }

=======
>>>>>>> Stashed changes
}

