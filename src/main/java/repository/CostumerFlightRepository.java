package repository;

import database.DBConnector;
import model.Tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CostumerFlightRepository {

    public List<Tickets> searchAvailableTickets(String departure, String destination, LocalDate date, int passengers) {
        List<Tickets> tickets = new ArrayList<>();

        String query = """
            SELECT t.flightnumber,
                   f.departureairport,
                   f.arrivalairport,
                   f.departuretime,
                   f.arrivaltime,
                   f.status,
                   f.duration,
                   a.airlinename,
                   t.ticketprice
            FROM tickets t
            JOIN flights f ON t.flightnumber = f.flightnumber
            JOIN airline a ON f.airlineid = a.airlineid
            WHERE f.departureairport = ?
              AND f.arrivalairport = ?
              AND DATE(f.departuretime) = ?
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, departure);
            stmt.setString(2, destination);
            stmt.setDate(3, Date.valueOf(date));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tickets ticket = new Tickets();
                ticket.setFlightNumber(rs.getInt("flightnumber"));
                ticket.setDepartureAirport(rs.getString("departureairport"));
                ticket.setArrivalAirport(rs.getString("arrivalairport"));
                ticket.setDepartureTime(rs.getTimestamp("departuretime"));
                ticket.setArrivalTime(rs.getTimestamp("arrivaltime"));
                ticket.setStatus(rs.getString("status"));
                ticket.setDuration(rs.getString("duration"));
                ticket.setAirlineName(rs.getString("airlinename"));
                ticket.setTicketPrice(rs.getDouble("ticketprice"));

                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
