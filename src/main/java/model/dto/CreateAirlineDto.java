package model.dto;

public class CreateAirlineDto {
    private String airlinename;
    private String country;
    private String email;
    private String password;
    private String phoneNumber;

    public CreateAirlineDto() {}

    public CreateAirlineDto(String airlinename, String country, String email, String password, String phoneNumber) {
        this.airlinename = airlinename;
        this.country = country;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
