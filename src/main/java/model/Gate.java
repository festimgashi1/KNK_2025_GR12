package model;

public class Gate {

    private int gateId;
    private int flightNumber;
    private String gateNumber;
    private String status;

    public Gate(int gateId, int flightNumber, String gateNumber, String status) {
        this.gateId = gateId;
        this.flightNumber = flightNumber;
        this.gateNumber = gateNumber;
        this.status = status;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getStatus() {
        return status;
    }
}
