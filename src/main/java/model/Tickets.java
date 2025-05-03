package model;

import java.util.Date;

public class Tickets {
    private int ticketid;
    private int flightNumber;
    private int customerid;
    private Date bookingdate;
    private double ticketprice;
    private String paymentmethod;

    protected Tickets(int ticketid, int flightNumber, int customerid, Date bookingdate, double ticketprice, String paymentmethod) {
        this.ticketid = ticketid;
        this.flightNumber = flightNumber;
        this.customerid = customerid;
        this.bookingdate = bookingdate;
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

    public Date getBookingdate() {
        return bookingdate;
    }

    public double getTicketprice() {
        return ticketprice;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }
}

