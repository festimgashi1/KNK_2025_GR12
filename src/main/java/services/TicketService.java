package services;

import repository.TicketRepository;
import model.Tickets;

import java.util.List;

public class TicketService {

    private final TicketRepository ticketRepository = new TicketRepository();

    // Funksioni për të shtuar një biletë duke përdorur objektin `Tickets`
    public void addTicket(Tickets ticket) {
        // Krijo biletën dhe ruaje në databazë
        ticketRepository.addTicket(ticket);
    }

    public List<Tickets> getTicketsByAirlineId(int airlineId) {
        return ticketRepository.getTicketsByAirlineId(airlineId);
    }

    public void deleteTicket(int ticketId) {
        ticketRepository.deleteTicket(ticketId);
    }

    public void updateTicket(Tickets ticket) {
        ticketRepository.updateTicket(ticket);
    }
}
