package session;

public class AirlineSession {
    private static int airlineId;
    private static String airlineName; // ➕ Shto këtë

    public static void setAirlineId(int id) {
        airlineId = id;
    }

    public static int getAirlineId() {
        return airlineId;
    }

    public static void setAirlineName(String name) {
        airlineName = name;
    }

    public static String getAirlineName() {
        return airlineName;
    }

    public static void clear() {
        airlineId = 0;
        airlineName = null; // pastron edhe emrin
    }
}
