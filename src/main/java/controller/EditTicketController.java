package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Tickets;
import services.TicketService;
import services.LanguageManager;

import java.util.ResourceBundle;

public class EditTicketController {

    @FXML private TextField txtPrice;
    @FXML private TextField txtPassengers;
    @FXML private Label lblError;
    @FXML private Label lblHeader;
    @FXML private Label lblTicketPrice;
    @FXML private Label lblMaxPassengers;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    private Tickets ticket;
    private final TicketService ticketService = new TicketService();

    @FXML
    public void initialize() {
        loadTranslations();
        LanguageManager.getInstance().addListener(this::loadTranslations);
    }

    private void loadTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblHeader.setText(bundle.getString("edit.ticket.header"));
        lblTicketPrice.setText(bundle.getString("edit.ticket.price"));
        lblMaxPassengers.setText(bundle.getString("edit.ticket.passengers"));
        txtPrice.setPromptText(bundle.getString("edit.ticket.prompt.price"));
        txtPassengers.setPromptText(bundle.getString("edit.ticket.prompt.passengers"));
        btnSave.setText(bundle.getString("button.save"));
        btnCancel.setText(bundle.getString("button.cancel"));
    }

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
            lblError.setText(LanguageManager.getInstance().getResourceBundle().getString("edit.ticket.error"));
            lblError.setVisible(true);
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) txtPrice.getScene().getWindow();
        stage.close();
    }
}
