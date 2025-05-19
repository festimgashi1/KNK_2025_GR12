package repository;

import database.DBConnector;
import model.Flights;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public void save(Flights flight) {
        String sql = """
            INSERT INTO flights (
                flightnumber, airlineid, planeid,
                departureairport, arrivalairport,
                departuretime, arrivaltime,
                duration, status
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, flight.getFlightNumber());
            stmt.setInt(2, flight.getAirlineid());
            stmt.setInt(3, flight.getPlaneid());
            stmt.setString(4, flight.getDepartureAirport());
            stmt.setString(5, flight.getArrivalAirport());

            Timestamp departureTimestamp = Timestamp.valueOf(LocalDateTime.parse(flight.getDepartureTime(), FORMATTER));
            Timestamp arrivalTimestamp = Timestamp.valueOf(LocalDateTime.parse(flight.getArrivalTime(), FORMATTER));

            stmt.setTimestamp(6, departureTimestamp);
            stmt.setTimestamp(7, arrivalTimestamp);
            stmt.setString(8, flight.getDuration());
            stmt.setString(9, flight.getStatus());

            stmt.executeUpdate();
            System.out.println("Flight inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Flights> getFlightsByAirlineId(int airlineId) {
        List<Flights> flights = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE airlineid = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, airlineId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flights flight = new Flights(
                        rs.getInt("flightnumber"),
                        rs.getInt("airlineid"),
                        rs.getInt("planeid"),
                        rs.getString("departureairport"),
                        rs.getString("arrivalairport"),
                        rs.getTimestamp("departuretime").toLocalDateTime().format(FORMATTER),
                        rs.getTimestamp("arrivaltime").toLocalDateTime().format(FORMATTER),
                        rs.getString("duration"),
                        null,
                        rs.getString("status")

                );
                flights.add(flight);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }


    public boolean deleteById(int flightNumber) {
        String query = "DELETE FROM flights WHERE flightnumber = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, flightNumber);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateFlight(Flights flight) {
        String sql = """
            UPDATE flights
            SET planeid = ?, departureairport = ?, arrivalairport = ?,
                departuretime = ?, arrivaltime = ?, duration = ?, status = ?
            WHERE flightnumber = ?
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, flight.getPlaneid());
            stmt.setString(2, flight.getDepartureAirport());
            stmt.setString(3, flight.getArrivalAirport());

            Timestamp departureTimestamp = Timestamp.valueOf(LocalDateTime.parse(flight.getDepartureTime(), FORMATTER));
            Timestamp arrivalTimestamp = Timestamp.valueOf(LocalDateTime.parse(flight.getArrivalTime(), FORMATTER));

            stmt.setTimestamp(4, departureTimestamp);
            stmt.setTimestamp(5, arrivalTimestamp);
            stmt.setString(6, flight.getDuration());
            stmt.setString(7, flight.getStatus());

            stmt.setInt(8, flight.getFlightNumber());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


