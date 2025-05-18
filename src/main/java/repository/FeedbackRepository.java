package repository;



import database.DBConnector;
import model.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FeedbackRepository {

    public boolean saveFeedback(Feedback feedback) {
        String query = "INSERT INTO Feedback (costumerid, flightnumber, rating, comments) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, feedback.getCostumerId());
            stmt.setObject(2, null); // vendoset 0 nëse s’ka flight
            stmt.setString(3, feedback.getRating());
            stmt.setString(4, feedback.getComments());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

