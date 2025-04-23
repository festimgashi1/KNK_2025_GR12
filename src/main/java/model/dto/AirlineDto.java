package model.dto;

public class AirlineDto {
    private int airlineid;
    private String airlinename;
    private String country;
    private String email;
    private String phoneNumber;

    public AirlineDto() {}

    public AirlineDto(int airlineid, String airlinename, String country, String email, String phoneNumber) {
        this.airlineid = airlineid;
        this.airlinename = airlinename;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getAirlineid() {
        return airlineid;
    }

    public void setAirlineid(int airlineid) {
        this.airlineid = airlineid;
    }

    public String getAirlinename() {
        return airlinename;
    }

    public void setAirlinename(String airlinename) {
        this.airlinename = airlinename;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
