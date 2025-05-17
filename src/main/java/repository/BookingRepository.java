package repository;

import database.DBConnector;
import model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingRepository {

    public boolean insertBooking(Booking booking) {
        String query = "INSERT INTO booking (costumerid, flightnumber, departuredate, destination, price, seatnumber) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, booking.getCostumerId());
            stmt.setInt(2, booking.getFlightNumber());
            stmt.setDate(3, java.sql.Date.valueOf(booking.getDepartureDate()));
            stmt.setString(4, booking.getDestination());
            stmt.setDouble(5, booking.getPrice());
            stmt.setString(6, booking.getSeatNumber());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting booking: " + e.getMessage());
            return false;
        }
    }
}
