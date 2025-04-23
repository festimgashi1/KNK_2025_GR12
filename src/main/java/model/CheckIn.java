package model;

import java.sql.Date;

public class CheckIn {
   private int id;
   private int ticketid;
   private int flightid;
   private Date checkinTime;
   private String seatNumber;
   private String method;

    public CheckIn(int id, int ticketid, int flightid, Date checkinTime, String seatNumber, String method) {
        this.id = id;
        this.ticketid = ticketid;
        this.flightid = flightid;
        this.checkinTime = checkinTime;
        this.seatNumber = seatNumber;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public int getTicketid() {
        return ticketid;
    }

    public int getFlightid() {
        return flightid;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getMethod() {
        return method;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public void setFlightid(int flightid) {
        this.flightid = flightid;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
