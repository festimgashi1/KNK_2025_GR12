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
            SELECT t.* FROM tickets t
            JOIN flights f ON t.flightNumber = f.flightNumber
            WHERE f.departure = ? AND f.destination = ? AND f.departure_date = ? AND t.passengers >= ?
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
                        rs.getInt("customerid"),
                        rs.getDate("bookingdate"),
                        rs.getDouble("ticketprice"),
                        rs.getString("paymentmethod"),
                        rs.getInt("passengers")
                );
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
