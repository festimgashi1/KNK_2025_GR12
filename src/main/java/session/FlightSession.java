package session;

import model.Flights;

public class FlightSession {
    private static FlightSession instance;
    private Flights currentFlight;

    private FlightSession() {}

    public static FlightSession getInstance() {
        if (instance == null) {
            instance = new FlightSession();
        }
        return instance;
    }

    public void setFlight(Flights flight) {
        this.currentFlight = flight;
    }

    public Flights getFlight() {
        return currentFlight;
    }

    public void clear() {
        this.currentFlight = null;
    }
}
