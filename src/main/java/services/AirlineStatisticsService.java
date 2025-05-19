package services;

import repository.AirlineStatisticsRepository;

import java.time.LocalDate;
import java.util.Map;

public class AirlineStatisticsService {

    private final AirlineStatisticsRepository repo = new AirlineStatisticsRepository();

    public int getTotalFlights(int airlineId, LocalDate start, LocalDate end) {
        return repo.countTotalFlights(airlineId, start, end);
    }

    public int getTotalReservations(int airlineId, LocalDate start, LocalDate end) {
        return repo.countTotalReservations(airlineId, start, end);
    }

    public int getTotalPassengers(int airlineId, LocalDate start, LocalDate end) {
        return repo.countTotalPassengers(airlineId, start, end);
    }

    public Map<String, Integer> getReservationDistribution(int airlineId, LocalDate start, LocalDate end) {
        return repo.getReservationStatusCounts(airlineId, start, end);
    }

    public Map<String, Integer> getPassengersPerFlight(int airlineId, LocalDate start, LocalDate end) {
        return repo.getPassengersGroupedByFlight(airlineId, start, end);
    }
}
