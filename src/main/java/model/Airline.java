package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Airline {
    private int airlineid;
    private String airlinename;
    private String country;
    private String email;
    private String hashpassword;
    private String salt;
    private String phoneNumber;

    public Airline(int airlineid, String airlinename, String country, String email, String hashpassword, String salt,String phoneNumber) {
        this.airlineid = airlineid;
        this.airlinename = airlinename;
        this.country = country;
        this.email = email;
        this.hashpassword = hashpassword;
        this.salt=salt;
        this.phoneNumber = phoneNumber;
    }

    public static Airline getInstance(ResultSet result) throws SQLException{
        int airlineid = result.getInt("airlineid");
        String airlinename = result.getString("airlinename");
        String country = result.getString("country");
        String email = result.getString("email");
        String hashpassword = result.getString("hashpassword");
        String salt = result.getString("salt");
        String phonenumber = result.getString("phonenumber");
        return new Airline(airlineid,airlinename,country,email,hashpassword,salt,phonenumber);
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return hashpassword;
    }

    public String getSalt() { return salt; }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
