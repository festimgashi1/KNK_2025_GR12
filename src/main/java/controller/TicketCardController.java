package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Tickets;
import services.SceneManager;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class TicketCardController {

    @FXML private Label lblPrice;
    @FXML private Label lblFrom;
    @FXML private Label lblTo;
    @FXML private Label lblDepartureTime;
    @FXML private Label lblArrivalTime;
    @FXML private Label lblDuration;
    @FXML private Label lblStatus;

    private Tickets ticket;

    public void setTicketData(Tickets ticket) {
        this.ticket = ticket;

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        lblPrice.setText("€" + ticket.getTicketPrice());
        lblFrom.setText(ticket.getDepartureAirport());
        lblTo.setText(ticket.getArrivalAirport());
        lblDepartureTime.setText(timeFormat.format(ticket.getDepartureTime()));
        lblArrivalTime.setText(timeFormat.format(ticket.getArrivalTime()));
       
        lblStatus.setText(ticket.getStatus());
    }

    @FXML
    private void handleBuyClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/reserve_ticket.fxml"));
            Parent root = loader.load();

            // Kalo të dhënat e biletës te faqja tjetër nëse do
            SceneManager.getInstance().setData("selectedTicket", ticket);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Confirm Booking");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
