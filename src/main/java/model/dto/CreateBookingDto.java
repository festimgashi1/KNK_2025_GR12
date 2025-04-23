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


}
