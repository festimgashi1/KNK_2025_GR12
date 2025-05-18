package model.dto;

import java.time.LocalDate;

public class CreateBookingDto {
    private int costumerId;
    private int flightNumber;
    private LocalDate departureDate;
    private String destination;
    private double price;
    private String seatNumber;

    public CreateBookingDto(int costumerId, int flightNumber, LocalDate departureDate, String destination, double price, String seatNumber) {
        this.costumerId = costumerId;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.destination = destination;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
