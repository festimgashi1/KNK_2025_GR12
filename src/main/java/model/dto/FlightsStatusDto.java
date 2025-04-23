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

    public Long getFlightsId() {
        return flightsId;
    }

    public void setFlightsId(Long flightsId) {
        this.flightsId = flightsId;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public LocalDateTime getDataNisjes() {
        return dataNisjes;
    }

    public void setDataNisjes(LocalDateTime dataNisjes) {
        this.dataNisjes = dataNisjes;
    }

    public LocalDateTime getDataArritjes() {
        return dataArritjes;
    }

    public void setDataArritjes(LocalDateTime dataArritjes) {
        this.dataArritjes = dataArritjes;
    }

    public String getAeroportiNisjes() {
        return aeroportiNisjes;
    }

    public void setAeroportiNisjes(String aeroportiNisjes) {
        this.aeroportiNisjes = aeroportiNisjes;
    }

    public String getAeroportiArritjes() {
        return aeroportiArritjes;
    }

    public void setAeroportiArritjes(String aeroportiArritjes) {
        this.aeroportiArritjes = aeroportiArritjes;
    }
}
