package model;

public class Flights {
    private int flightNumber;
    private int airlineid;
    private int planeid;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String status;
    private String airlineName;

    public Flights(int flightNumber, int airlineid, int planeid, String departureAirport,
                   String arrivalAirport, String departureTime, String arrivalTime, String duration,
                   String airlineName, String status) {
        this.flightNumber = flightNumber;
        this.airlineid = airlineid;
        this.planeid = planeid;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.airlineName = airlineName;
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

    public String getAirlineName() {
        return airlineName;
    }

    public void setPlaneid(int planeid) {
        this.planeid = planeid;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
