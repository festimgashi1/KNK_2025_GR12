package repository;

import database.DBConnector;
import model.Tickets;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketCardRepository {

    public void addTicket(Tickets ticket) {
        String query = "INSERT INTO Tickets (flightnumber, ticketprice, passengers, bookingdate, paymentmethod, customerid) " +
                "VALUES (?, ?, ?, ?, NULL, NULL)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, ticket.getFlightNumber());
            statement.setDouble(2, ticket.getTicketPrice());
            statement.setInt(3, ticket.getPassengers());
            statement.setDate(4, new java.sql.Date(ticket.getBookingdate().getTime()));

            statement.executeUpdate();
            System.out.println("Ticket added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tickets> getTicketsByAirlineId(int airlineId) {
        List<Tickets> tickets = new ArrayList<>();

        String query = "SELECT t.ticketid, t.flightnumber, t.ticketprice, t.passengers, " +
                "f.departureairport, f.arrivalairport, f.departuretime, f.arrivaltime, f.status " +
                "FROM Tickets t " +
                "JOIN Flights f ON t.flightnumber = f.flightnumber " +
                "WHERE f.airlineid = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, airlineId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tickets ticket = new Tickets(
                        rs.getInt("ticketid"),
                        rs.getInt("flightnumber"),
                        0,
                        null,
                        rs.getDouble("ticketprice"),
                        "",
                        rs.getInt("passengers")
                );

                ticket.setDepartureAirport(rs.getString("departureairport"));
                ticket.setArrivalAirport(rs.getString("arrivalairport"));
                ticket.setDepartureTime(rs.getTimestamp("departuretime"));
                ticket.setArrivalTime(rs.getTimestamp("arrivaltime"));
                ticket.setStatus(rs.getString("status"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public void deleteTicket(int ticketId) {
        String query = "DELETE FROM Tickets WHERE ticketid = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ticketId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTicket(Tickets ticket) {
        String query = "UPDATE tickets SET ticketprice = ?, passengers = ? WHERE ticketid = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, ticket.getTicketPrice());
            stmt.setInt(2, ticket.getPassengers());
            stmt.setInt(3, ticket.getTicketid());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tickets> findTicketsByCriteria(String from, String to, LocalDate date, int passengerCount) {
        List<Tickets> tickets = new ArrayList<>();
        String sql = "SELECT t.ticketid, t.flightNumber, t.bookingdate, t.ticketprice, t.passengers, " +
                "f.departureAirport, f.arrivalAirport, f.departureTime, f.arrivalTime, f.duration, " +
                "a.airlinename, f.status " +
                "FROM Tickets t " +
                "JOIN Flights f ON t.flightNumber = f.flightNumber " +
                "JOIN Airline a ON f.airlineid = a.airlineid " +
                "WHERE f.departureAirport = ? AND f.arrivalAirport = ? AND DATE(f.departureTime) = ? AND t.passengers >= ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, from);
            stmt.setString(2, to);
            stmt.setDate(3, Date.valueOf(date));
            stmt.setInt(4, passengerCount);

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
                ticket.setStatus(rs.getString("status"));
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
