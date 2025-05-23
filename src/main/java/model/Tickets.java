package model;

import java.util.Date;

public class Tickets {
    private int ticketid;
    private int flightNumber;
    private int customerid;
    private Date bookingdate;
    private double ticketprice;
    private String paymentmethod;
    private int passengers;

    private String departureAirport;
    private String arrivalAirport;
    private Date departureTime;
    private Date arrivalTime;
    private String status;
    private String airlineName;
    private String duration;

    public Tickets() {
    }

    public Tickets(int ticketid, int flightNumber, int customerid, Date bookingdate, double ticketprice, String paymentmethod, int passengers) {
        this.ticketid = ticketid;
        this.flightNumber = flightNumber;
        this.customerid = customerid;
        this.bookingdate = bookingdate;
        this.ticketprice = ticketprice;
        this.paymentmethod = paymentmethod;
        this.passengers = passengers;
    }

    public int getTicketid() {
        return ticketid;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getCustomerid() {
        return customerid;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public double getTicketPrice() {
        return ticketprice;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketprice = ticketPrice;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
