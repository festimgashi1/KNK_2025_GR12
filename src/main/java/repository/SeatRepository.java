package repository;

import database.DBConnector;
import java.sql.*;

public class SeatRepository {

    public void insertIfNotExists(String seatNumber, int flightNumber) {
        String checkQuery = "SELECT 1 FROM Seat WHERE seatNumber = ? AND flightNumber = ?";
        String insertQuery = "INSERT INTO Seat(seatNumber, classType, isAvailable, flightNumber) VALUES (?, 'Economy', true, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setString(1, seatNumber);
            checkStmt.setInt(2, flightNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, seatNumber);
                    insertStmt.setInt(2, flightNumber);
                    insertStmt.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Seat check/insert failed: " + e.getMessage());
        }
    }
}
