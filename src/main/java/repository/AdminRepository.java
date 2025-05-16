package repository;

import database.DBConnector;
import model.Admin;
import services.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {

    public Admin getEmail(String email) {
        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT * FROM admin WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Admin.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(int adminId, String currentPassword, String newPassword) {
        try (Connection conn = DBConnector.getConnection()) {
            String checkQuery = "SELECT hashpassword, salt FROM admin WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, adminId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("hashpassword");
                String storedSalt = rs.getString("salt");

                if (!PasswordHasher.compareSaltedHash(currentPassword.trim(), storedSalt, storedHash)) {
                    return false;
                }
            }

            String newSalt = PasswordHasher.generateSalt();
            String newHash = PasswordHasher.generateSaltedHash(newPassword.trim(), newSalt);

            String updateQuery = "UPDATE admin SET hashpassword = ?, salt = ? WHERE id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1, newHash);
            updateStmt.setString(2, newSalt);
            updateStmt.setInt(3, adminId);
            updateStmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
