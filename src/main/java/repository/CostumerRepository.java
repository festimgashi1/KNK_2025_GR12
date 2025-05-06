package repository;

import database.DBConnector;
import model.Costumer;
import model.dto.CostumerDto;
import model.dto.CreateCostumerDto;
import model.dto.UpdateCostumerDto;

import java.sql.*;



public class CostumerRepository extends BaseRepository<Costumer, CostumerDto, UpdateCostumerDto> {

    public CostumerRepository() {
        super("costumers");
    }

    @Override
    public Costumer fromResultSet(ResultSet result) throws SQLException {
        return Costumer.getInstance(result); // make sure you have this static method in your Costumer model
    }



    @Override
    public Costumer create(CostumerDto createDto) {
        String query= """
                insert into 
                Costumer (firstName, lastName, email, telephoneNumber, birthDate, hashpassword, salt, addres_id)
                values (?,?,?,?,?,?,?,?)
                """;

        try{

            PreparedStatement pstm=
                    this.connection.prepareStatement(
                            query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getFirstName());
            pstm.setString(2,createDto.getLastName());
            pstm.setString(3,createDto.getEmail());
            pstm.setString(4,createDto.getPhoneNumber());
            pstm.setDate(5,java.sql.Date.valueOf(createDto.getBirthdate()));
            pstm.setString(6,createDto.getHashPass());
            pstm.setString(7,createDto.getSalt());
            pstm.setInt(8,createDto.getAddress());

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

    @Override
    public Costumer update(UpdateCostumerDto updateDto) {


        return null;
    }


    public Costumer getByEmail(String email){
        String query = "SELECT * FROM Costumer" + " WHERE email = ?";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query);
            pstm.setString(1, email);
            ResultSet res = pstm.executeQuery();
            if(res.next()){
                return this.fromResultSet(res);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public Costumer update(UpdateCostumerDto dto) {
//        String query = """
//                UPDATE COSTUMERS
//                SET EMAIL = ?, TELEPHONE_NUMBER = ?, ADDRESS = ?
//                WHERE COSTUMER_ID = ?
//                """;
//        try {
//            PreparedStatement pstm = this.connection.prepareStatement(query);
//            pstm.setString(1, dto.getEmail());
//            pstm.setInt(2, dto.getTelephoneNumber());
//            pstm.setString(3, dto.getAddress());
//            pstm.setInt(4, dto.getCostumerId());
//
//            int updated = pstm.executeUpdate();
//            if (updated == 1) {
//                return this.getById(dto.getCostumerId());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }


}
