package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PendingAirline {

    private int id;
    private StringProperty airlineName;
    private StringProperty country;
    private StringProperty email;
    private StringProperty phoneNumber;
    private String hashpassword;
    private String salt;
    private LocalDate requestDate;
    private String status;

    public PendingAirline(int id, String airlineName, String country, String email, String phoneNumber, String hashpassword, String salt, LocalDate requestDate, String status) {
        this.id = id;
        this.airlineName = new SimpleStringProperty(airlineName);
        this.country = new SimpleStringProperty(country);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.hashpassword = hashpassword;
        this.salt = salt;
        this.requestDate = requestDate;
        this.status = status;
    }

    public static PendingAirline getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String airlineName = result.getString("airline_name");
        String country = result.getString("country");
        String email = result.getString("email");
        String phoneNumber = result.getString("phone_number");
        String hashpassword = result.getString("password_hash");
        String salt = result.getString("salt");
        LocalDate requestDate = result.getDate("request_date").toLocalDate();
        String status = result.getString("status");
        return new PendingAirline(id, airlineName, country, email, phoneNumber, hashpassword, salt, requestDate, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName.get();
    }

    public StringProperty airlineNameProperty() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName.set(airlineName);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getHashpassword() {
        return hashpassword;
    }

    public void setHashpassword(String hashpassword) {
        this.hashpassword = hashpassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
