package repository;

import database.DBConnector;
import model.Tickets;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CostumerFlightRepository {
    public List<Tickets> searchAvailableTickets(String departure, String destination, LocalDate departureDate, int passengers) {
        List<Tickets> tickets = new ArrayList<>();
        String query = """
            SELECT t.ticketid, t.flightNumber, t.bookingdate, t.ticketprice, t.passengers,
                   f.departureAirport, f.arrivalAirport, f.departureTime, f.arrivalTime, f.duration,
                   a.airlinename
            FROM Tickets t
            JOIN Flights f ON t.flightNumber = f.flightNumber
            JOIN Airline a ON f.airlineid = a.airlineid
            WHERE f.departureAirport = ? AND f.arrivalAirport = ? AND DATE(f.departureTime) = ? AND t.passengers >= ?
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, departure);
            stmt.setString(2, destination);
            stmt.setDate(3, Date.valueOf(departureDate));
            stmt.setInt(4, passengers);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tickets ticket = new Tickets(
                        rs.getInt("ticketid"),
                        rs.getInt("flightNumber"),
                        0,
                        rs.getDate("bookingdate"),
                        rs.getDouble("ticketprice"),
                        "",
                        rs.getInt("passengers")
                );
                ticket.setDepartureAirport(rs.getString("departureAirport"));
                ticket.setArrivalAirport(rs.getString("arrivalAirport"));
                ticket.setDepartureTime(rs.getTimestamp("departureTime"));
                ticket.setArrivalTime(rs.getTimestamp("arrivalTime"));
                ticket.setDuration(rs.getString("duration"));
                ticket.setAirlineName(rs.getString("airlinename"));
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}

