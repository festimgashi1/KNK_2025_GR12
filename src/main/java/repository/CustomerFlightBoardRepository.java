package repository;


import model.Flights;
import database.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFlightBoardRepository {
    private final Connection connection;

    public CustomerFlightBoardRepository() {
        this.connection = DBConnector.getConnection();
    }

    public List<Flights> getArrivalsToday(String airport) {
        List<Flights> flights = new ArrayList<>();
        String query = "SELECT flightnumber, departureairport, arrivalairport, departuretime, arrivaltime, status " +
                "FROM flights " +
                "WHERE arrivalAirport = ? AND departureAirport <> ? AND DATE(arrivalTime) = CURRENT_DATE";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, airport);
            stmt.setString(2, airport);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        rs.getTimestamp("departuretime").toString(),
                        rs.getTimestamp("arrivaltime").toString(),
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
                "WHERE departureAirport = ? AND arrivalAirport <> ? AND DATE(departureTime) = CURRENT_DATE";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, airport);
            stmt.setString(2, airport);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        rs.getTimestamp("departuretime").toString(),
                        rs.getTimestamp("arrivaltime").toString(),
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
                "WHERE arrivalAirport = ? AND departureAirport <> ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, airport);
            stmt.setString(2, airport);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        rs.getTimestamp("departuretime").toString(),
                        rs.getTimestamp("arrivaltime").toString(),
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
                "WHERE departureAirport = ? AND arrivalAirport <> ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, airport);
            stmt.setString(2, airport);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        0,
                        0,
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        rs.getTimestamp("departuretime").toString(),
                        rs.getTimestamp("arrivaltime").toString(),
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

