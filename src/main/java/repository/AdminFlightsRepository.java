package repository;

import model.Flights;
import database.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminFlightsRepository {

    private final Connection connection;

    public AdminFlightsRepository() {
        this.connection = DBConnector.getConnection();
    }

    public List<Flights> getAll() {
        List<Flights> flights = new ArrayList<>();
        String query = "SELECT * FROM flights";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        rs.getInt("airlineid"),
                        rs.getInt("planeid"),
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        rs.getString("departuretime"),
                        rs.getString("arrivaltime"),
                        rs.getString("duration"),
                        null,
                        rs.getString("status")

                );
                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public boolean deleteByFlightNumber(int flightNumber) {
        String query = "DELETE FROM flights WHERE flightNumber = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, flightNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
