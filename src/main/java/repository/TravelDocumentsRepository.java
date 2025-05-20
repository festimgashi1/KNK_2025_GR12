package repository;

import database.DBConnector;
import model.TravelDocuments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TravelDocumentsRepository {

    public void save(TravelDocuments doc) {
        String query = "INSERT INTO TravelDocuments (costumerId, issueDate, expiryDate) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, doc.getCostumerId());
            stmt.setDate(2, new java.sql.Date(doc.getIssueDate().getTime()));
            stmt.setDate(3, new java.sql.Date(doc.getExpiryDate().getTime()));

            stmt.executeUpdate();
            System.out.println("Travel document saved successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save travel document: " + e.getMessage());
        }
    }
}
