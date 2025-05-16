package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Tickets;
import services.TicketService;

public class EditTicketController {

    @FXML private TextField txtPrice;
    @FXML private TextField txtPassengers;
    @FXML private Label lblError;

    private Tickets ticket;
    private final TicketService ticketService = new TicketService();

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
        txtPrice.setText(String.valueOf(ticket.getTicketPrice()));
        txtPassengers.setText(String.valueOf(ticket.getPassengers()));
    }

    @FXML
    private void handleSave() {
        try {
            double price = Double.parseDouble(txtPrice.getText());
            int passengers = Integer.parseInt(txtPassengers.getText());

            ticket.setTicketPrice(price);
            ticket.setPassengers(passengers);

            ticketService.updateTicket(ticket);

            Stage stage = (Stage) txtPrice.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            lblError.setText("Please enter valid numbers.");
            lblError.setVisible(true);
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) txtPrice.getScene().getWindow();
        stage.close();
    }
}
