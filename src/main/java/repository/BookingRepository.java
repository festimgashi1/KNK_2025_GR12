package repository;

import model.dto.CreateBookingDto;
import database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookingRepository {

    public boolean createBooking(CreateBookingDto dto) {
        String query = """
            INSERT INTO Booking (costumerId, flightNumber, departureDate, destination, price, seatNumber)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, dto.getCostumerId());
            stmt.setInt(2, dto.getFlightNumber());
            stmt.setDate(3, java.sql.Date.valueOf(dto.getDepartureDate()));
            stmt.setString(4, dto.getDestination());
            stmt.setDouble(5, dto.getPrice());
            stmt.setString(6, dto.getSeatNumber());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
