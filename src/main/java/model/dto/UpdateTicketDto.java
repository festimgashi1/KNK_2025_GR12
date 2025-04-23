package model.dto;

public class UpdateTicketDto {
    private String seatNumber;
    private double price;
    private String status;





    public UpdateTicketDto(String seatNumber, double price, String status) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
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
