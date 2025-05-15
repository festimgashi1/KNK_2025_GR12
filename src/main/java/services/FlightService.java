package services;

import model.Flights;
import repository.FlightRepository;

public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService() {
        this.flightRepository = new FlightRepository();
    }

    public void createFlight(Flights flight) {
        flightRepository.save(flight);
    }
}
