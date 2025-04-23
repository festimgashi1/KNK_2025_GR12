package model.dto;

import java.time.LocalDate;

public class CreateClientDto {
private String firstName;
private String lastName;
private String phoneNumber;
private String address;
private String city;
private String country;
private String postalClient;
private LocalDate registrationDate;
private String passportNumber;
private String nationality;


    public CreateClientDto( String firstName, String lastName, String phoneNumber, String address, String city, String country, String postalClient, LocalDate registrationDate, String passportNumber, String nationality) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalClient = postalClient;
        this.registrationDate = registrationDate;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalClient() {
        return postalClient;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalClient(String postalClient) {
        this.postalClient = postalClient;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
