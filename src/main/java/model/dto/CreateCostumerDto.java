package model.dto;

import java.time.LocalDate;

public class CreateCostumerDto {
private String firstName;
private String lastName;
private String email;
private String phoneNumber;
private String address;
private LocalDate birthDate;
private String password;
private String confirmPassword;



    public CreateCostumerDto(String firstName, String lastName, String email, String phoneNumber, String address, LocalDate birthDate, String password, String confirmPassword) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
       this.email=email;
       this.birthDate=birthDate;
       this.password=password;
       this.confirmPassword=confirmPassword;
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

    public String getEmail() { return email; }

    public LocalDate getBirthDate() { return birthDate; }

    public String getPassword() { return password; }

    public String getConfirmPassword() { return confirmPassword; }

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

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
