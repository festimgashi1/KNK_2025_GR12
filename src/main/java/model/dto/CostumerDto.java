package model.dto;

import java.time.LocalDate;

public class CostumerDto {

    String firstName;
    String lastName;
    String email;
    String address;
    LocalDate birthdate;
    String salt;
    String hashPass;
    String phoneNumber;

    public CostumerDto(String firstName, String lastName, String email, String address, LocalDate birthdate, String salt, String hashPass, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
        this.salt = salt;
        this.hashPass = hashPass;
        this.phoneNumber=phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getSalt() {
        return salt;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }
}
