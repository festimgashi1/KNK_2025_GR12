package repository;

import database.DBConnector;
import model.Costumer;
import model.dto.CreateCostumerDto;
import model.dto.UpdateCostumerDto;

import java.sql.*;

public class CostumerRepository extends BaseRepository<Costumer, CreateCostumerDto, UpdateCostumerDto> {

    public CostumerRepository() {
        super("costumers");
    }

    @Override
    public Costumer fromResultSet(ResultSet result) throws SQLException {
        return Costumer.getInstance(result); // make sure you have this static method in your Costumer model
    }

    @Override
    public Costumer create(CreateCostumerDto dto) {
        String query = """
                INSERT INTO COSTUMERS 
                (FIRST_NAME, LAST_NAME, EMAIL, TELEPHONE_NUMBER, GENDER, BIRTH_DATE, HASH_PASSWORD, SALT, ADDRESS)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getFirstName());
            pstm.setString(2, dto.getLastName());
            pstm.setString(3, dto.getEmail());
            pstm.setString(4, dto.getPhoneNumber());
            pstm.setDate(6, dto.getBirthDate());
            pstm.setString(7, dto.getHash);
            pstm.setString(8, dto.getSalt());
            pstm.setString(9, dto.getAddress());

            pstm.execute();

            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Costumer update(UpdateCostumerDto dto) {
        String query = """
                UPDATE COSTUMERS 
                SET EMAIL = ?, TELEPHONE_NUMBER = ?, ADDRESS = ?
                WHERE COSTUMER_ID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEmail());
            pstm.setInt(2, dto.getTelephoneNumber());
            pstm.setString(3, dto.getAddress());
            pstm.setInt(4, dto.getCostumerId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getCostumerId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
