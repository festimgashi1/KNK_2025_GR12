package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashpass;
    private String salt;
    private int addressId;

    protected Admin(int id, String firstName, String lastName, String email, String hashpass, String salt, int addressId){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.hashpass=hashpass;
        this.salt=salt;
        this.addressId=addressId;
    }

    public static Admin getInstance (ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");
        String email = result.getString("email");
        String hashpass = result.getString("hashpass");
        String salt = result.getString("salt");
        int addressId = result.getInt("addressId");
        return new Admin(id,firstName,lastName,email,hashpass,salt,addressId);
    }

    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getHashpass(){
        return hashpass;
    }
    public String getSalt(){
        return salt;
    }
    public int getAddressId(){
        return addressId;
    }
}
