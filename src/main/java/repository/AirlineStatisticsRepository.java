package repository;

import database.DBConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AirlineStatisticsRepository {

    private static LocalDate safeStart(LocalDate start) {
        return start != null ? start : LocalDate.of(2000, 1, 1);
    }

    private static LocalDate safeEnd(LocalDate end) {
        return end != null ? end : LocalDate.of(2100, 1, 1);
    }


    public int countTotalFlights(int airlineId, LocalDate start, LocalDate end) {
        String query = "SELECT COUNT(*) FROM flights WHERE airlineid = ? AND departuretime BETWEEN ? AND ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, airlineId);
            stmt.setTimestamp(2, Timestamp.valueOf(safeStart(start).atStartOfDay()));
            stmt.setTimestamp(3, Timestamp.valueOf(safeEnd(end).atTime(23, 59, 59)));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countTotalReservations(int airlineId, LocalDate start, LocalDate end) {
        String query = "SELECT COUNT(*) FROM booking " +
                "WHERE flightnumber IN (" +
                "SELECT flightnumber FROM flights WHERE airlineid = ? AND DATE(departuretime) BETWEEN ? AND ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, airlineId);
            stmt.setTimestamp(2, Timestamp.valueOf(safeStart(start).atStartOfDay()));
            stmt.setTimestamp(3, Timestamp.valueOf(safeEnd(end).atTime(23, 59, 59)));


            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Integer> getReservationsGroupedByFlight(int airlineId, LocalDate start, LocalDate end) {
        String query = "SELECT f.flightnumber, COUNT(b.bookingid) AS reservation_count " +
                "FROM flights f LEFT JOIN booking b ON f.flightnumber = b.flightnumber " +
                "WHERE f.airlineid = ? AND DATE(f.departuretime) BETWEEN ? AND ? " +
                "GROUP BY f.flightnumber";

        Map<String, Integer> data = new HashMap<>();
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, airlineId);
            stmt.setTimestamp(2, Timestamp.valueOf(safeStart(start).atStartOfDay()));
            stmt.setTimestamp(3, Timestamp.valueOf(safeEnd(end).atTime(23, 59, 59)));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String flight = "FL-" + rs.getInt("flightnumber");
                int count = rs.getInt("reservation_count");
                data.put(flight, count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


    public Map<String, Integer> getReservationStatusCounts(int airlineId, LocalDate start, LocalDate end) {
        String query = "SELECT status, COUNT(*) FROM booking " +
                "WHERE flightnumber IN (" +
                "SELECT flightnumber FROM flights WHERE airlineid = ? AND DATE(departuretime) BETWEEN ? AND ?) " +
                "GROUP BY status";

        Map<String, Integer> data = new HashMap<>();
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, airlineId);
            stmt.setTimestamp(2, Timestamp.valueOf(safeStart(start).atStartOfDay()));
            stmt.setTimestamp(3, Timestamp.valueOf(safeEnd(end).atTime(23, 59, 59)));


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                data.put(rs.getString("status"), rs.getInt(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Map<String, Integer> getPassengersGroupedByFlight(int airlineId, LocalDate start, LocalDate end) {
        String query = "SELECT f.flightnumber, COALESCE(SUM(t.passengers), 0) AS total_passengers " +
                "FROM flights f LEFT JOIN tickets t ON f.flightnumber = t.flightnumber " +
                "WHERE f.airlineid = ? AND DATE(f.departuretime) BETWEEN ? AND ? " +
                "GROUP BY f.flightnumber";

        Map<String, Integer> data = new HashMap<>();
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, airlineId);
            stmt.setTimestamp(2, Timestamp.valueOf(safeStart(start).atStartOfDay()));
            stmt.setTimestamp(3, Timestamp.valueOf(safeEnd(end).atTime(23, 59, 59)));


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String flight = "Flight " + rs.getInt("flightnumber");
                int passengers = rs.getInt("total_passengers");
                data.put(flight, passengers);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
