package model.dto;

public class AirportStaffDto {
    private int AirportStaffId;


    private String firstName;


    private String lastName;


    private String email;


    private String phone;


    private String position;

    private boolean isActive; // statusi i stafit (aktiv ose joaktiv)



    public int getId() {
        return AirportStaffId;
    }

    public void setId(int AirportstaffId) {
        this.AirportStaffId = AirportstaffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

