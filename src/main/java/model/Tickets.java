package model;

import java.util.Date;

public class Tickets {
    private int ticketid;
    private int flightNumber;
    private int customerid;
    private int airlineid;
    private Date bookingdate;
    private int seatnumber;
    private double ticketprice;
    private String paymentmethod;

    protected Tickets(int ticketid, int flightNumber, int customerid, int airlineid, Date bookingdate, int seatnumber, double ticketprice, String paymentmethod) {
        this.ticketid = ticketid;
        this.flightNumber = flightNumber;
        this.customerid = customerid;
        this.airlineid = airlineid;
        this.bookingdate = bookingdate;
        this.seatnumber = seatnumber;
        this.ticketprice = ticketprice;
        this.paymentmethod = paymentmethod;
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

    public int getAirlineid() {
        return airlineid;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public int getSeatnumber() {
        return seatnumber;
    }

    public double getTicketprice() {
        return ticketprice;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }
}

