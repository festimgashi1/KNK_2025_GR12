package model;

public class flights {
    private int flightNumber;
    private int airlineid;
    private int planeid;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String status;

    protected flights(int flightNumber, int airlineid, int planeid, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime, String duration, String status) {
        this.flightNumber = flightNumber;
        this.airlineid = airlineid;
        this.planeid = planeid;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.status = status;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getAirlineid() {
        return airlineid;
    }

    public int getPlaneid() {
        return planeid;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }
}
