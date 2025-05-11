package repository;

import database.DBConnector;
import model.Airline;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApprovedAirlinesRepository {

    private final Connection connection;

    public ApprovedAirlinesRepository() {
        this.connection = DBConnector.getConnection();
    }

    public List<Airline> getAllApproved() {
        List<Airline> airlines = new ArrayList<>();
        String query = "SELECT * FROM airline";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Airline airline = new Airline(
                        rs.getInt("airlineid"),
                        rs.getString("airlinename"),
                        rs.getString("country"),
                        rs.getString("email"),
                        rs.getString("hashpassword"),
                        rs.getString("salt"),
                        rs.getString("phonenumber")
                );
                airlines.add(airline);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airlines;
    }
}
