package model.dto;

public class UpdateAirlineDto {
    private String airlinename;
    private String country;
    private String email;
    private String phoneNumber;


    public UpdateAirlineDto() {}


    public UpdateAirlineDto(String airlinename, String country, String email, String phoneNumber) {
        this.airlinename = airlinename;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

