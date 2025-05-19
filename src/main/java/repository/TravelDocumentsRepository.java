package repository;

import database.DBConnector;
import model.TravelDocuments;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TravelDocumentsRepository {

    public void save(TravelDocuments doc) {
        String query = "INSERT INTO TravelDocuments (costumerId, bookingId, issueDate, expiryDate) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, doc.getKlientId());
            stmt.setInt(2, doc.getBookingId());
            stmt.setDate(3, new java.sql.Date(doc.getIssueDate().getTime()));
            stmt.setDate(4, new java.sql.Date(doc.getExpiryDate().getTime()));

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert travel document: " + e.getMessage());
        }
    }
}
