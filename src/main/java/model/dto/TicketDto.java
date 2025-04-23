package model.dto;

public class TicketDto {
    private int ticketId;
    private String flightNumber;
    private String passengerName;
    private String seatNumber;
    private double price;
    private String status;

    // Default constructor

    // Constructor për TicketDto
    public TicketDto(int ticketId, String flightNumber, String passengerName, String seatNumber, double price, String status) {
        this.ticketId = ticketId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
    }

    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
