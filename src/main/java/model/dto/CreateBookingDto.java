package model.dto;

import java.time.LocalDateTime;

public class CreateBookingDto {
    private Long costumerId;

    private Long flightsNumber;
    private LocalDateTime dataENisjes;
    private String destinacioni;
    private Double cmimi;

    public CreateBookingDto(Long costumerId, Long flightsNumber, LocalDateTime dataENisjes, String destinacioni, Double cmimi) {
        this.costumerId = costumerId;
        this.flightsNumber = flightsNumber;
        this.dataENisjes = dataENisjes;
        this.destinacioni = destinacioni;
        this.cmimi = cmimi;
    }

    public Long getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Long costumerId) {
        this.costumerId = costumerId;
    }

    public Long getFlightsNumber() {
        return flightsNumber;
    }

    public void setFlightsNumber(Long flightsNumber) {
        this.flightsNumber = flightsNumber;
    }

    public LocalDateTime getDataENisjes() {
        return dataENisjes;
    }

    public void setDataENisjes(LocalDateTime dataENisjes) {
        this.dataENisjes = dataENisjes;
    }

    public String getDestinacioni() {
        return destinacioni;
    }

    public void setDestinacioni(String destinacioni) {
        this.destinacioni = destinacioni;
    }

    public Double getCmimi() {
        return cmimi;
    }

    public void setCmimi(Double cmimi) {
        this.cmimi = cmimi;
    }
}
