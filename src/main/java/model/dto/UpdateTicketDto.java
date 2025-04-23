package model.dto;

public class UpdateTicketDto {
    private String seatNumber; // Numri i ri i vendit
    private double price; // Çmimi i ri i biletës
    private String status; // Statusi i ri i biletës (p.sh. "konfirmuar", "anuluar", etj.)


    public UpdateTicketDto() {}


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
