package repository;

import model.Airline;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AirlineRepository {
    private List<Airline> airlines;
    private static AirlineRepository instance;


    private AirlineRepository() {
        this.airlines = new ArrayList<>();
        initializeSampleAirlines();
    }

    public static synchronized AirlineRepository getInstance() {
        if (instance == null) {
            instance = new AirlineRepository();
        }
        return instance;
    }

    private void initializeSampleAirlines() {

        addAirline(new Airline(1, "Adria Airways", "Slovenia", 1001, "adria123","blla" , "+38612345678"));
        addAirline(new Airline(2, "Austrian Airlines", "Austria", 1002, "austrian456","blla", "+4312345678"));
        addAirline(new Airline(3, "Turkish Airlines", "Turkey", 1003, "turkish789","blla", "+9012345678"));
        addAirline(new Airline(4, "Lufthansa", "Germany", 1004, "lufthansa012","blla", "+4912345678"));
    }



    public List<Airline> getAllAirlines() {
        return new ArrayList<>(airlines);
    }

    public Optional<Airline> getAirlineById(int airlineid) {
        return airlines.stream()
                .filter(a -> a.getAirlineid() == airlineid)
                .findFirst();
    }

    public List<Airline> getAirlinesByCountry(String country) {
        return airlines.stream()
                .filter(a -> a.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    public Optional<Airline> getAirlineByEmail(int email) {
        return airlines.stream()
                .filter(a -> a.getEmail() == email)
                .findFirst();
    }

    public boolean addAirline(Airline airline) {
        if (airline == null ||
                airline.getAirlinename() == null ||
                airline.getCountry() == null ||
                airline.getPhoneNumber() == null) {
            return false;
        }


        boolean exists = airlines.stream()
                .anyMatch(a -> a.getAirlineid() == airline.getAirlineid() ||
                        a.getEmail() == airline.getEmail());

        if (!exists) {
            return airlines.add(airline);
        }
        return false;
    }

    public boolean updateAirline(Airline updatedAirline) {
        if (updatedAirline == null) {
            return false;
        }

        for (int i = 0; i < airlines.size(); i++) {
            if (airlines.get(i).getAirlineid() == updatedAirline.getAirlineid()) {
                airlines.set(i, updatedAirline);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAirline(int airlineid) {
        return airlines.removeIf(a -> a.getAirlineid() == airlineid);
    }


    public Optional<Airline> authenticate(int email, String password) {
        return airlines.stream()
                .filter(a -> a.getEmail() == email && a.getPassword().equals(password))
                .findFirst();
    }


    public boolean phoneNumberExists(String phoneNumber) {
        return airlines.stream()
                .anyMatch(a -> a.getPhoneNumber().equals(phoneNumber));
    }

    public boolean emailExists(int email) {
        return airlines.stream()
                .anyMatch(a -> a.getEmail() == email);
    }
}
