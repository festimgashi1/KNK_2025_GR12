package repository;

import database.DBConnector;
import model.Tickets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private Connection connection;

    public TicketRepository() {
        this.connection = DBConnector.getConnection();
    }


    public void addTicket(Tickets ticket) {
        String query = """
            INSERT INTO Tickets (flightnumber, ticketprice, passengers, bookingdate, paymentmethod, customerid)
            VALUES (?, ?, ?, ?, NULL, NULL)  -- Lëjmë paymentmethod dhe customerid të zbrazët
        """;

        try {
            PreparedStatement statement = connection.prepareStatement(query);


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
                        0,                     // customerid
                        null,                  // bookingdate
                        rs.getDouble("ticketprice"),
                        "",                    // paymentmethod
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
}
