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

    // Funksioni për të shtuar biletën me të dhënat e nevojshme
    public void addTicket(Tickets ticket) {
        String query = """
            INSERT INTO Tickets (flightnumber, ticketprice, passengers, bookingdate, paymentmethod, customerid)
            VALUES (?, ?, ?, ?, NULL, NULL)  -- Lëjmë paymentmethod dhe customerid të zbrazët
        """;

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            // Vendosim të dhënat për flightNumber, ticketPrice dhe passengers
            statement.setInt(1, ticket.getFlightNumber());  // flightnumber
            statement.setDouble(2, ticket.getTicketprice());  // ticketprice
            statement.setInt(3, ticket.getPassengers());  // passengers
            statement.setDate(4, new java.sql.Date(ticket.getBookingdate().getTime()));  // bookingdate

            // Ekzekutojmë query-n për shtimin e biletës
            statement.executeUpdate();  // Execute the insert query
            System.out.println("Ticket added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Funksioni për të marrë biletat për një airline të caktuar
    public List<Tickets> getTicketsByAirline(int airlineId) {
        List<Tickets> ticketList = new ArrayList<>();
        String query = """
            SELECT t.ticketid, t.flightNumber, t.bookingdate, t.ticketprice, t.passengers,
                   f.departureairport, f.arrivalairport, f.departuretime, f.arrivaltime, f.status
            FROM Tickets t
            JOIN Flights f ON t.flightnumber = f.flightnumber
            WHERE f.airlineid = ?
        """;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, airlineId);  // Përdorim airlineId për të filtruar
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Krijo një objekt Tickets dhe mbush me të dhëna nga tabela
                int ticketid = resultSet.getInt("ticketid");
                int flightNumber = resultSet.getInt("flightnumber");
                java.sql.Date bookingDate = resultSet.getDate("bookingdate");
                double ticketPrice = resultSet.getDouble("ticketprice");
                int passengers = resultSet.getInt("passengers");
                String departureAirport = resultSet.getString("departureairport");
                String arrivalAirport = resultSet.getString("arrivalairport");
                java.sql.Timestamp departureTime = resultSet.getTimestamp("departuretime");
                java.sql.Timestamp arrivalTime = resultSet.getTimestamp("arrivalime");
                String status = resultSet.getString("status");

                // Krijo një objekt Tickets dhe shto në listë
                Tickets ticket = new Tickets(ticketid, flightNumber, 0, bookingDate, ticketPrice, "", passengers);
                ticket.setDepartureAirport(departureAirport);
                ticket.setArrivalAirport(arrivalAirport);
                ticket.setDepartureTime(departureTime);
                ticket.setArrivalTime(arrivalTime);
                ticket.setStatus(status);

                ticketList.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketList;
    }
}
