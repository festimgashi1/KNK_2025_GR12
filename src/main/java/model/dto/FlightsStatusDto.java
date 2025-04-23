package model.dto;

import java.time.LocalDateTime;

public class FlightsStatusDto {
    private Long flightsId;
    private String statusi;
    private LocalDateTime dataNisjes;
    private LocalDateTime dataArritjes;
    private String aeroportiNisjes;
    private String aeroportiArritjes;

    public FlightsStatusDto(Long flightsId, String statusi, LocalDateTime dataNisjes, LocalDateTime dataArritjes, String aeroportiNisjes, String aeroportiArritjes) {
        this.flightsId = flightsId;
        this.statusi = statusi;
        this.dataNisjes = dataNisjes;
        this.dataArritjes = dataArritjes;
        this.aeroportiNisjes = aeroportiNisjes;
        this.aeroportiArritjes = aeroportiArritjes;
    }
}
