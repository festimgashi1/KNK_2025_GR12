package repository;

import database.DBConnector;
import model.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackRepository {

    public boolean saveFeedback(Feedback feedback) {
        String sql = """
            INSERT INTO Feedback (costumerId, flightNumber, rating, comments)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, feedback.getCostumerId());
            stmt.setInt(2, feedback.getFlightNumber());
            stmt.setString(3, feedback.getRating());
            stmt.setString(4, feedback.getComments());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error while saving feedback: " + e.getMessage());
            return false;
        }
    }
}
