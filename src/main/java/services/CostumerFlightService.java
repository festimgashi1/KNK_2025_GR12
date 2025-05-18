package services;

import model.Tickets;
import repository.CostumerFlightRepository;

import java.time.LocalDate;
import java.util.List;

public class CostumerFlightService {
    private final CostumerFlightRepository repository;

    public CostumerFlightService() {
        this.repository = new CostumerFlightRepository();
    }

    public List<Tickets> searchAvailableTickets(String departure, String destination, LocalDate departureDate, int passengers) {
        return repository.searchAvailableTickets(departure, destination, departureDate, passengers);
    }
}
