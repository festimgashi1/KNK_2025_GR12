package model.dto;

import java.time.LocalDate;

public class CreateClientDto {
private int clientId;
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


    public CreateClientDto(int clientId, String firstName, String lastName, String phoneNumber, String address, String city, String country, String postalClient, LocalDate registrationDate, String passportNumber, String nationality) {
        this.clientId = clientId;
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

}
