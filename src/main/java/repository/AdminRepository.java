package repository;
import database.DBConnector;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    protected Connection connection;

    public AdminRepository() {
        this.connection = DBConnector.getConnection();
    }

    public Admin getEmail(String email) {
        String query = "SELECT * FROM Admin WHERE email = ?";
        try {
            if (this.connection == null) {
                System.out.println("Database connection is null!");
                return null;
            }
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                return Admin.getInstance(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
