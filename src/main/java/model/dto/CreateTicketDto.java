package model.dto;

public class CreateTicketDto {
    private String seatNumber;
    private double price;
    private String status;

    // Default constructor
    public UpdateTicketDto() {}

    // Constructor për UpdateTicketDto
    public UpdateTicketDto(String seatNumber, double price, String status) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
    }

    // Getters and Setters
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
