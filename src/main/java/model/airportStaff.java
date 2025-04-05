package model;
import java.time.LocalDate;

public class airportStaff {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String role;
    private int addressId;
    private LocalDate startedAt;
    private String shift;

    protected airportStaff(int id, String firstName, String lastName, int phoneNumber, String role, int addressId, LocalDate startedAt, String shift){
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.role=role;
        this.addressId=addressId;
        this.startedAt=startedAt;
        this.shift=shift;
    }

    public int getId(){
        return id;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getRole(){
        return role;
    }
    public int getAddressId(){
        return addressId;
    }
    public LocalDate getStartedAt(){
        return startedAt;
    }
    public String getShift(){
        return shift;
    }
}
