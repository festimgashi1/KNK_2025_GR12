package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AirportStaff {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private String address;
    private LocalDate startedAt;
    private String shift;

    // Konstruktor për INSERT (pa id)
    public AirportStaff(String firstName, String lastName, String phoneNumber,
                        String role, String address, LocalDate startedAt, String shift) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.address = address;
        this.startedAt = startedAt;
        this.shift = shift;
    }

    // Konstruktor për SELECT (me id)
    public AirportStaff(int id, String firstName, String lastName, String phoneNumber,
                        String role, String address, LocalDate startedAt, String shift) {
        this(firstName, lastName, phoneNumber, role, address, startedAt, shift);
        this.id = id;
    }

    // Krijimi nga ResultSet (SELECT)
    public static AirportStaff getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");
        String phoneNumber = result.getString("phoneNumber");
        String role = result.getString("role");
        String address = result.getString("address"); // kontrollo nëse duhet të merret nga JOIN me Address
        LocalDate startedAt = result.getDate("startedAt").toLocalDate();
        String shift = result.getString("shift");
        return new AirportStaff(id, firstName, lastName, phoneNumber, role, address, startedAt, shift);
    }

    // Getters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getRole() { return role; }
    public String getAddress() { return address; }
    public LocalDate getStartedAt() { return startedAt; }
    public String getShift() { return shift; }
}
