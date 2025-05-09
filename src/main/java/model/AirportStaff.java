package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AirportStaff {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String role;
    private String address;
    private static LocalDate startedAt;
    private String shift;

    protected AirportStaff(int id, String firstName, String lastName, int phoneNumber, String role, String address, LocalDate startedAt, String shift){
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.role=role;
        this.address=address;
        this.startedAt=startedAt;
        this.shift=shift;
    }

    public static AirportStaff getInstance (ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");
        int phoneNumber = result.getInt("phoneNumber");
        String role = result.getString("role");
        String address = result.getString("address");
        LocalDate startedAt = LocalDate.parse(result.getString("startedAt"));
        String shift = result.getString("shift");
        return new AirportStaff(id,firstName,lastName,phoneNumber,role,address,startedAt,shift);
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
    public LocalDate getStartedAt(){
        return startedAt;
    }
    public String getShift(){
        return shift;
    }

    public String getAddress() {
        return address;
    }
}