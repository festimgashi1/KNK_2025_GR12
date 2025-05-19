package repository;

import model.dto.CreateBookingDto;
import database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

            System.out.println("🚀 Executing INSERT into Booking:");
            System.out.println("→ costumerId = " + dto.getCostumerId());
            System.out.println("→ flightNumber = " + dto.getFlightNumber());
            System.out.println("→ departureDate = " + dto.getDepartureDate());
            System.out.println("→ destination = " + dto.getDestination());
            System.out.println("→ price = " + dto.getPrice());
            System.out.println("→ seatNumber = " + dto.getSeatNumber());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Rows affected: " + rows);
            return rows > 0;

        } catch (SQLException e) {
            System.err.println(" SQL ERROR while inserting booking: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println(" Unexpected ERROR while inserting booking: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
