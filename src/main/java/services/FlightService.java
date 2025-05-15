package services;

import model.Flights;
import repository.FlightRepository;

import java.util.List;

public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService() {
        this.flightRepository = new FlightRepository();
    }

    public void createFlight(Flights flight) {
        flightRepository.save(flight);
    }


    public List<Flights> getFlightsByAirlineId(int airlineId) {
        return flightRepository.getFlightsByAirlineId(airlineId);
    }


    public boolean deleteFlightById(int flightNumber) {
        return flightRepository.deleteById(flightNumber);
    }


    public boolean updateFlight(Flights flight) {
        return flightRepository.updateFlight(flight);
    }
}

