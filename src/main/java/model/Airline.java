package model;

public class Airline {
    private int airlineid;
    private String airlinename;
    private String country;
    private int email;
    private String password;
    private String phoneNumber;

    public Airline(int airlineid, String airlinename, String country, int email, String password, String phoneNumber) {
        this.airlineid = airlineid;
        this.airlinename = airlinename;
        this.country = country;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public int getAirlineid() {
        return airlineid;
    }

    public String getAirlinename() {
        return airlinename;
    }

    public String getCountry() {
        return country;
    }

    public int getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
