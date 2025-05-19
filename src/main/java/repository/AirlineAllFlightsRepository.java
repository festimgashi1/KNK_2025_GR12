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
        String query = "SELECT flightnumber, departureairport, arrivalairport, departuretime, arrivaltime, status " +
                "FROM flights " +
                "WHERE LOWER(arrivalAirport) IN ('prishtina', 'prishtine') AND LOWER(departureAirport) NOT IN ('prishtina', 'prishtine') " +
                "AND DATE(arrivalTime) = CURRENT_DATE";

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
        String query = "SELECT flightnumber, departureairport, arrivalairport, departuretime, arrivaltime, status " +
                "FROM flights " +
                "WHERE LOWER(departureAirport) IN ('prishtina', 'prishtine') AND LOWER(arrivalAirport) NOT IN ('prishtina', 'prishtine') " +
                "AND DATE(departureTime) = CURRENT_DATE";

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
        String query = "SELECT flightnumber, departureairport, arrivalairport, departuretime, arrivaltime, status " +
                "FROM flights " +
                "WHERE LOWER(arrivalAirport) IN ('prishtina', 'prishtine') AND LOWER(departureAirport) NOT IN ('prishtina', 'prishtine')";

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
        String query = "SELECT flightnumber, departureairport, arrivalairport, departuretime, arrivaltime, status " +
                "FROM flights " +
                "WHERE LOWER(departureAirport) IN ('prishtina', 'prishtine') AND LOWER(arrivalAirport) NOT IN ('prishtina', 'prishtine')";

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
