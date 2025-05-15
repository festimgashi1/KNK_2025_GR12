package repository;


import model.BoardingPass;
import model.dto.CreateBoardingPassDto;
import database.DBConnector;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardingPassRepository {
    private final Connection connection = DBConnector.getConnection();

    // INSERT – krijon një rezervim të ri
    public BoardingPass save(CreateBoardingPassDto dto) throws Exception {
        String sql = """
            INSERT INTO Reservation (name, from_location, to_location, flight_date, flight_code, gate, boarding_time, seat)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING ReservationId;
        """;

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, dto.getName());
        stmt.setString(2, dto.getFrom());
        stmt.setString(3, dto.getTo());
        stmt.setDate(4, Date.valueOf(dto.getFlightDate()));
        stmt.setString(5, dto.getFlightCode());
        stmt.setString(6, dto.getGate());
        stmt.setString(7, dto.getBoardingTime());
        stmt.setString(8, dto.getSeat());

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int reservationId = rs.getInt("ReservationId");
            return new BoardingPass(reservationId, dto.getName(), dto.getFrom(), dto.getTo(), dto.getFlightDate(),
                    dto.getFlightCode(), dto.getGate(), dto.getBoardingTime(), dto.getSeat());
        }

        throw new Exception("Failed to insert reservation");
    }

    // SELECT – merr të gjitha rezervimet
    public List<BoardingPass> getAll() throws Exception {
        List<BoardingPass> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int reservationId = rs.getInt("ReservationId");
            String name = rs.getString("name");
            String from = rs.getString("from_location");
            String to = rs.getString("to_location");
            LocalDate flightDate = rs.getDate("flight_date").toLocalDate();
            String flightCode = rs.getString("flight_code");
            String gate = rs.getString("gate");
            String boardingTime = rs.getString("boarding_time");
            String seat = rs.getString("seat");

            BoardingPass reservation = new BoardingPass(reservationId, name, from, to, flightDate, flightCode, gate, boardingTime, seat);
            reservations.add(reservation);
        }

        return reservations;
    }
}
