package session;

public class FlightSession {

    private static int flightNumber; // ose mundesh me e bo long nëse e ke të tillë në DB

    public static void setFlightNumber(int number) {
        flightNumber = number;
    }

    public static int getFlightNumber() {
        return flightNumber;
    }

    public static void clearSession() {
        flightNumber = 0;
    }
}