package repository;

import database.DBConnector;
import model.BoardingPass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardingPassRepository {

    public BoardingPass getBoardingPassByReservationId(int reservationId) {
        String query = "SELECT * FROM BoardingPass WHERE reservationId = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new BoardingPass(
                        rs.getInt("reservationId"),
                        rs.getString("name"),
                        rs.getString("from_location"),
                        rs.getString("to_location"),
                        rs.getDate("flight_date").toLocalDate(),
                        rs.getString("flight_code"),
                        rs.getString("gate"),
                        rs.getString("boarding_time"),
                        rs.getString("seat")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void save(BoardingPass pass) {
        String query = "INSERT INTO BoardingPass (name, from_location, to_location, flight_date, flight_code, gate, boarding_time, seat) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

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
            throw new RuntimeException("Failed to insert boarding pass: " + e.getMessage());
        }
    }
}
