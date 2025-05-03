package model;

public class Seat {
    private String seatNumber;
    private String classType;
    private boolean isAvailable;
    private int flightNumber;
    private int ticketId;


    public Seat(String seatNumber, String classType, boolean isAvailable, int flightNumber, int ticketId) {
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.isAvailable = isAvailable;
        this.flightNumber= flightNumber;
        this.ticketId=ticketId;
    }


    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getTicketId() { return ticketId; }

    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

}
