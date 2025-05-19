package repository;

import database.DBConnector;
import model.BoardingPass;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BoardingPassRepository {

    public void save(BoardingPass pass) {
        String sql = "INSERT INTO BoardingPass (name, from_location, to_location, flight_date, flight_code, gate, boarding_time, seat) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pass.getName());
            stmt.setString(2, pass.getFrom());
            stmt.setString(3, pass.getTo());
            stmt.setDate(4, java.sql.Date.valueOf(pass.getFlightDate()));
            stmt.setString(5, pass.getFlightCode());
            stmt.setString(6, pass.getGate());
            stmt.setString(7, pass.getBoardingTime());
            stmt.setString(8, pass.getSeat());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save boarding pass: " + e.getMessage());
        }
    }
}