package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Tickets;
import services.TicketService;

import java.util.Date;

public class AddTicketController {

    @FXML private TextField txtFlightNumber;
    @FXML private TextField txtTicketPrice;
    @FXML private TextField txtMaxPassengers;

    @FXML private Label lblError;
    @FXML private Label lblSuccess;

    private final TicketService ticketService = new TicketService();

    @FXML
    private void handleSubmit() {
        String flightNumber = txtFlightNumber.getText();
        String price = txtTicketPrice.getText();
        String maxPassengers = txtMaxPassengers.getText();

        // Kontrollo nëse fushat janë të mbushura
        if (flightNumber.isEmpty() || price.isEmpty() || maxPassengers.isEmpty()) {
            lblError.setText("All fields are required!");
            lblError.setVisible(true);
            lblSuccess.setVisible(false);
            return;
        }

        try {
            double ticketPrice = Double.parseDouble(price);  // Konverto në double për çmimin
            int passengers = Integer.parseInt(maxPassengers);  // Konverto në integer për numrin e pasagjerëve

            // Krijo objektin `Tickets`
            Tickets ticket = new Tickets(0, Integer.parseInt(flightNumber), 0, new Date(), ticketPrice, "", passengers);

            // Shto biletën përmes TicketService
            ticketService.addTicket(ticket);

            // Trego mesazhin e suksesit
            lblSuccess.setText("Ticket added successfully.");
            lblSuccess.setVisible(true);
            lblError.setVisible(false);

            // Pasi bileta është shtuar, mund të ndodhi ngarkimi i ri i tabelës për të shfaqur biletat e airline specifik (me `airlineId`)
            int airlineId = 1;  // Kjo mund të merret nga sesioni i përdoruesit
            loadTickets(airlineId); // Kjo do të duhet të jetë një metodë që ngarkon biletat në tabelë.

        } catch (NumberFormatException e) {
            lblError.setText("Invalid input format.");
            lblError.setVisible(true);
            lblSuccess.setVisible(false);
        }
    }

    // Kjo metodë mund të përdoret për të ngarkuar biletat e airline specifik
    private void loadTickets(int airlineId) {
        // Ky kod do të thërrasë shërbimin për të marrë biletat për një airline specifik
        // Të dhënat mund të shfaqen në një tabelë TableView që lidhet me këtë metodë
        // Mund të përdorni `TicketService` për të marrë biletat që përkasin airline-it të caktuar
        System.out.println("Loading tickets for airline with ID: " + airlineId);
        // Pasi të merrni të dhënat nga `TicketService`, mund t’i shfaqni në `TableView`
    }
}
