package session;

public class AirlineSession {
    private static int airlineId;

    public static void setAirlineId(int id) {
        airlineId = id;
    }

    public static int getAirlineId() {
        return airlineId;
    }

    public static void clear() {
        airlineId = 0;
    }
}
