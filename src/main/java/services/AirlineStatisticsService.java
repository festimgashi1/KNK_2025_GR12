package services;

import repository.AirlineStatisticsRepository;

import java.time.LocalDate;
import java.util.Map;

public class AirlineStatisticsService {

    private final AirlineStatisticsRepository statisticsRepository = new AirlineStatisticsRepository();

    public Map<String, Integer> getReservationsGroupedByFlight(int airlineId, LocalDate start, LocalDate end) {
        return statisticsRepository.getReservationsGroupedByFlight(airlineId, start, end);
    }


    public int getTotalFlights(int airlineId, LocalDate start, LocalDate end) {
        return statisticsRepository.countTotalFlights(airlineId, start, end);
    }

    public int getTotalReservations(int airlineId, LocalDate start, LocalDate end) {
        return statisticsRepository.countTotalReservations(airlineId, start, end);
    }
}
