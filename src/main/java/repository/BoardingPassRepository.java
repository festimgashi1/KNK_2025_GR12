package repository;

import database.DBConnector;
import model.BoardingPass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class BoardingPassRepository {

    public BoardingPass getBoardingPassByReservationId(int id) {
        String query = """
            SELECT reservationid, name, fromcode, tocode, flightdate, flightcode, gate, boardingtime, seat
            FROM boardingpass
            WHERE reservationid = ?
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new BoardingPass(
                        rs.getInt("reservationid"),
                        rs.getString("name"),
                        rs.getString("fromcode"),
                        rs.getString("tocode"),
                        rs.getDate("flightdate").toLocalDate(),
                        rs.getString("flightcode"),
                        rs.getString("gate"),
                        rs.getString("boardingtime"),
                        rs.getString("seat")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
