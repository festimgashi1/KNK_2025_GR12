package repository;

import database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class StatisticsRepository {

    public Map<String, Integer> getTicketsByPaymentMethod() {
        Map<String, Integer> result = new HashMap<>();
        String query = "SELECT paymentmethod, COUNT(*) AS count FROM tickets GROUP BY paymentmethod";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.put(rs.getString("paymentmethod"), rs.getInt("count"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Map<String, Integer> getFlightsPerAirline() {
        Map<String, Integer> result = new HashMap<>();
        String query = """
            SELECT a.airlinename, COUNT(f.flightNumber) AS count
            FROM Airline a
            JOIN Flights f ON a.airlineid = f.airlineid
            GROUP BY a.airlinename
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.put(rs.getString("airlinename"), rs.getInt("count"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
