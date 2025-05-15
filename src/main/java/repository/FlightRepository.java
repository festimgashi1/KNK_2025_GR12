package repository;

import database.DBConnector;
import model.Flights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;

public class FlightRepository {

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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Timestamp departureTimestamp = Timestamp.valueOf(LocalDateTime.parse(flight.getDepartureTime(), formatter));
            Timestamp arrivalTimestamp = Timestamp.valueOf(LocalDateTime.parse(flight.getArrivalTime(), formatter));

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
}

