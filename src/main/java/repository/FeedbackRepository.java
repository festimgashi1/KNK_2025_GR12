package repository;

import database.DBConnector;
import model.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackRepository {
    public boolean saveFeedback(Feedback feedback) {
        String query = "INSERT INTO feedback (costumerid, rating, comments) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, feedback.getCostumerId());
            stmt.setString(2, feedback.getRating());
            stmt.setString(3, feedback.getComment());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
