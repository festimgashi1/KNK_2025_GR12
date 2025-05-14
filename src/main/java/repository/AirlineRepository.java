package repository;

import database.DBConnector;
import model.Airline;
import model.PendingAirline;
import model.dto.AirlineDto;

import java.sql.*;

public class AirlineRepository {
    protected Connection connection;
    public AirlineRepository(){
        this.connection= DBConnector.getConnection();
    }

    public Airline fromResultSet(ResultSet result) throws SQLException {
        return Airline.getInstance(result);
    }

    public boolean createFromPending(PendingAirline pendingAirline) {
        String query = """
                INSERT INTO Airline (airlinename, country, email, hashpassword, salt, phoneNumber)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, pendingAirline.getAirlineName());
            pstm.setString(2, pendingAirline.getCountry());
            pstm.setString(3, pendingAirline.getEmail());
            pstm.setString(4, pendingAirline.getHashpassword());
            pstm.setString(5, pendingAirline.getSalt());
            pstm.setString(6, pendingAirline.getPhoneNumber());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteById(int id) {
        String sql = "DELETE FROM Airline WHERE airlineid = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Airline create(AirlineDto createDto) {
        String query= """
                insert into
                Airline (airlinename, country, email, hashpassword, salt, phoneNumber)
                values (?,?,?,?,?,?)
                """;

        try{
            PreparedStatement pstm=
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getAirlinename());
            pstm.setString(2,createDto.getCountry());
            pstm.setString(3,createDto.getEmail());
            pstm.setString(4,createDto.getHashpass());
            pstm.setString(5,createDto.getSalt());
            pstm.setString(6,createDto.getPhoneNumber());

            pstm.execute();
            ResultSet resultSet=pstm.getGeneratedKeys();
            if(resultSet.next()){
                return this.fromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Airline getEmail(String email){
        String query = "SELECT * FROM Airline WHERE email = ?";
        try{
            if(this.connection == null){
                System.out.println("Database connection failed!");
                return null;
            }
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1,email);
            ResultSet result = pstm.executeQuery();

            if(result.next()){
                return Airline.getInstance(result);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
