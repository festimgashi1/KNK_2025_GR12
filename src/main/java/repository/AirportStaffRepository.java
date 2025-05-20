package repository;

import model.AirportStaff;
import database.DBConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirportStaffRepository {

    private final Connection connection;

    public AirportStaffRepository() {
        this.connection = DBConnector.getConnection();
    }

    public List<AirportStaff> getAll() {
        List<AirportStaff> staffList = new ArrayList<>();
        String query = "SELECT * FROM airportstaff";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AirportStaff staff = AirportStaff.getInstance(rs);
                staffList.add(staff);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }

    public List<AirportStaff> getByShift(String shift) {
        List<AirportStaff> staffList = new ArrayList<>();
        String query = "SELECT * FROM airportstaff WHERE shift = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shift);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AirportStaff staff = AirportStaff.getInstance(rs);
                staffList.add(staff);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }

    public boolean insert(AirportStaff staff) {
        String query = "INSERT INTO airportstaff (firstName, lastName, phoneNumber, role, startedAt, shift,address) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staff.getFirstName());
            stmt.setString(2, staff.getLastName());
            stmt.setString(3, staff.getPhoneNumber());
            stmt.setString(4, staff.getRole());
            stmt.setDate(5, Date.valueOf(staff.getStartedAt()));
            stmt.setString(6, staff.getShift());
            stmt.setString(7, staff.getAddress());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(int id) {
        String query = "DELETE FROM airportstaff WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
