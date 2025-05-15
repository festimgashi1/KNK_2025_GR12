package services;

import model.Tickets;
import repository.CostumerFlightRepository;
import java.time.LocalDate;
import java.util.Optional;
public class CostumerFlightService {

        private final CostumerFlightRepository repository;

        public CostumerFlightService() {
            this.repository = new CostumerFlightRepository();
        }

        public Optional<Tickets> findMatchingTicket(String departure, String destination, LocalDate departureDate, int passengers) {
            return repository.searchAvailableTicket(departure, destination, departureDate, passengers);

    }

}
