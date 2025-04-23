package model.dto;

 import java.time.LocalDateTime;

public class CreateFlightsStatusDto {
    private String statusi;
    private LocalDateTime dataNisjes;
    private LocalDateTime dataArritjes;
    private String aeroportiNisjes;
    private String aeroportiArritjes;




    public CreateFlightsStatusDto(String statusi, LocalDateTime dataNisjes, LocalDateTime dataArritjes, String aeroportiNisjes, String aeroportiArritjes) {
        this.statusi = statusi;
        this.dataNisjes = dataNisjes;
        this.dataArritjes = dataArritjes;
        this.aeroportiNisjes = aeroportiNisjes;
        this.aeroportiArritjes = aeroportiArritjes;
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
