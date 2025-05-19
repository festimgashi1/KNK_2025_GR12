package repository;

import model.Flights;
import database.DBConnector;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AirlineAllFlightsRepository {
    private final Connection connection;

    public AirlineAllFlightsRepository() {
        this.connection = DBConnector.getConnection();
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private String formatDateTime(Timestamp timestamp) {
        if (timestamp == null) return "";
        return formatter.format(timestamp.toLocalDateTime());
    }

    public List<Flights> getArrivalsToday(String airport) {
        List<Flights> flights = new ArrayList<>();
        String query = """
            SELECT f.flightnumber, f.departureairport, f.arrivalairport,
                   f.departuretime, f.arrivaltime, f.status, a.airlinename
            FROM Flights f
            JOIN Airline a ON f.airlineid = a.airlineid
            WHERE LOWER(f.arrivalAirport) IN ('prishtina', 'prishtine')
              AND LOWER(f.departureAirport) NOT IN ('prishtina', 'prishtine')
              AND DATE(f.arrivalTime) = CURRENT_DATE
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        formatDateTime(rs.getTimestamp("departuretime")),
                        formatDateTime(rs.getTimestamp("arrivaltime")),
                        null,
                        rs.getString("airlinename"),
                        rs.getString("status")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public List<Flights> getDeparturesToday(String airport) {
        List<Flights> flights = new ArrayList<>();
        String query = """
            SELECT f.flightnumber, f.departureairport, f.arrivalairport,
                   f.departuretime, f.arrivaltime, f.status, a.airlinename
            FROM Flights f
            JOIN Airline a ON f.airlineid = a.airlineid
            WHERE LOWER(f.departureAirport) IN ('prishtina', 'prishtine')
              AND LOWER(f.arrivalAirport) NOT IN ('prishtina', 'prishtine')
              AND DATE(f.departureTime) = CURRENT_DATE
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        formatDateTime(rs.getTimestamp("departuretime")),
                        formatDateTime(rs.getTimestamp("arrivaltime")),
                        null,
                        rs.getString("airlinename"),
                        rs.getString("status")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public List<Flights> getAllArrivals(String airport) {
        List<Flights> flights = new ArrayList<>();
        String query = """
            SELECT f.flightnumber, f.departureairport, f.arrivalairport,
                   f.departuretime, f.arrivaltime, f.status, a.airlinename
            FROM flights f
            JOIN airline a ON f.airlineid = a.airlineid
            WHERE LOWER(f.arrivalAirport) IN ('prishtina', 'prishtine')
              AND LOWER(f.departureAirport) NOT IN ('prishtina', 'prishtine')
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        formatDateTime(rs.getTimestamp("departuretime")),
                        formatDateTime(rs.getTimestamp("arrivaltime")),
                        null,
                        rs.getString("airlinename"),
                        rs.getString("status")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public List<Flights> getAllDepartures(String airport) {
        List<Flights> flights = new ArrayList<>();
        String query = """
            SELECT f.flightnumber, f.departureairport, f.arrivalairport,
                   f.departuretime, f.arrivaltime, f.status, a.airlinename
            FROM flights f
            JOIN airline a ON f.airlineid = a.airlineid
            WHERE LOWER(f.departureAirport) IN ('prishtina', 'prishtine')
              AND LOWER(f.arrivalAirport) NOT IN ('prishtina', 'prishtine')
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        formatDateTime(rs.getTimestamp("departuretime")),
                        formatDateTime(rs.getTimestamp("arrivaltime")),
                        null,
                        rs.getString("airlinename"),
                        rs.getString("status")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }
}
