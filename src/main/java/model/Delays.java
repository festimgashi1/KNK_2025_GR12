package model;

import java.util.Date;

public class Delays {
    private int DelayId;
    private int flightNumber;
    private int delayDuration;
    private Date date;
    private String status;

    public Delays(int delayId, int flightNumber, int delayDuration, Date date, String status) {
        DelayId = delayId;
        this.flightNumber = flightNumber;
        this.delayDuration = delayDuration;
        this.date = date;
        this.status = status;
    }

    public int getDelayId() {
        return DelayId;
    }

    public void setDelayId(int delayId) {
        DelayId = delayId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getDelayDuration() {
        return delayDuration;
    }

    public void setDelayDuration(int delayDuration) {
        this.delayDuration = delayDuration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
