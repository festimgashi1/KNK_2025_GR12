package model.dto;

public class CreatePendingAirlineDto {

    private String airlinename;
    private String country;
    private String email;
    private String hashpass;
    private String salt;
    private String phoneNumber;

    public CreatePendingAirlineDto() {}

    public CreatePendingAirlineDto(String airlinename, String country, String email, String hashpass, String salt, String phoneNumber) {
        this.airlinename = airlinename;
        this.country = country;
        this.email = email;
        this.hashpass = hashpass;
        this.salt = salt;
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

    public String getHashpass() {
        return hashpass;
    }

    public void setHashpass(String hashpass) {
        this.hashpass = hashpass;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}