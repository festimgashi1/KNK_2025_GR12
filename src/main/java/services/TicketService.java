package services;

import repository.TicketRepository;
import model.Tickets;

public class TicketService {

    private final TicketRepository ticketRepository = new TicketRepository();

    // Funksioni për të shtuar një biletë duke përdorur objektin `Tickets`
    public void addTicket(Tickets ticket) {
        // Krijo biletën dhe ruaje në databazë
        ticketRepository.addTicket(ticket);
    }
}
