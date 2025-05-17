package services;

import model.Airline;
import repository.AirlineRepository;

public class AirlineService {
    private final AirlineRepository repo = new AirlineRepository();

    public Airline getAirlineById(int id) {
        return repo.findById(id);
    }
}