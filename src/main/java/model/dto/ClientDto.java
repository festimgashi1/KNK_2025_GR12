package model.dto;

import java.time.LocalDate;
import java.util.List;

public class ClientDto {
    private int ClientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean isActive;
    private LocalDate registrationDate;
    private List<Long> bookingIds;

    public void setClientId(int ClientId) {
        this.ClientId = ClientId;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setBookingIds(List<Long> bookingIds) {
        this.bookingIds = bookingIds;
    }

    public int getClientId() {
        return ClientId;
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

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public List<Long> getBookingIds() {
        return bookingIds;
    }

    public ClientDto(int ClientId, String firstName, String lastName, String email, String phone, boolean isActive, LocalDate registrationDate, List<Long> bookingIds) {
        this.ClientId = ClientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
        this.registrationDate = registrationDate;
        this.bookingIds = bookingIds;
    }
}
