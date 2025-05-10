package repository;

import database.DBConnector;
import model.PendingAirline;
import model.dto.CreatePendingAirlineDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PendingAirlineRepository {
    protected Connection connection;

    public PendingAirlineRepository() {
        this.connection = DBConnector.getConnection();
    }

    public PendingAirline fromResultSet(ResultSet result) throws SQLException {
        return PendingAirline.getInstance(result);
    }

    public List<PendingAirline> getAll() {
        String query = "SELECT * FROM pending_airlines WHERE status = 'Pending'";  // Get only pending airlines
        List<PendingAirline> pendingAirlines = new ArrayList<>();
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                PendingAirline pendingAirline = fromResultSet(result);
                pendingAirlines.add(pendingAirline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingAirlines;
    }

    public PendingAirline create(CreatePendingAirlineDto createDto) {
        String query = """
                INSERT INTO pending_airlines 
                (airline_name, country, email, phone_number, password_hash, salt, request_date, status) 
                VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE, 'Pending')
                """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getAirlinename());
            pstm.setString(2, createDto.getCountry());
            pstm.setString(3, createDto.getEmail());
            pstm.setString(4, createDto.getPhoneNumber());
            pstm.setString(5, createDto.getHashpass());
            pstm.setString(6, createDto.getSalt());

            pstm.execute();
            ResultSet resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()) {
                return this.fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateStatusToApproved(int airlineId) {
        String query = "UPDATE pending_airlines SET status = 'Approved' WHERE id = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, airlineId);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateStatusToDenied(int airlineId) {
        String query = "UPDATE pending_airlines SET status = 'Denied' WHERE id = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, airlineId);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PendingAirline getByEmail(String email) {
        String query = "SELECT * FROM pending_airlines WHERE email = ?";
        try {
            if (this.connection == null) {
                System.out.println("Database connection failed!");
                return null;
            }
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                return PendingAirline.getInstance(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteByEmail(String email) {
        String query = "DELETE FROM pending_airlines WHERE email = ?";
        try {
            if (this.connection == null) {
                System.out.println("Database connection failed!");
                return false;
            }
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, email);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
