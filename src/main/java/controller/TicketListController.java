package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import model.Tickets;
import services.SceneManager;

import java.io.IOException;
import java.util.List;

public class TicketListController {

    @FXML
    private VBox ticketListContainer;



    @FXML
    private void initialize() {
        List<Tickets> tickets = (List<Tickets>) SceneManager.getInstance().getData("matchingTickets");
        if (tickets != null) {
            for (Tickets ticket : tickets) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/TicketCard.fxml"));
                    Parent ticketCard = loader.load();

                    TicketCardController controller = loader.getController();
                    controller.setTicketData(ticket);

                    ticketListContainer.getChildren().add(ticketCard);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
