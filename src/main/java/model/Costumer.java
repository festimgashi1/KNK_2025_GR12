package model;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Costumer {
    private int costumerId;
    private String firstName;
    private String lastName;
    private String email;
    private int telephoneNumber;
    private String gender;
    private Date birthDate;
    private String hashpassword;
    private String salt;
    private String address;


    protected Costumer(int costumerId ,String firstName, String lastName,int telephoneNumber, String email,String gender,Date birthDate,String hashpaswword, String salt ,String address ){
        this.costumerId=costumerId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.telephoneNumber=telephoneNumber;
        this.gender=gender;
        this.birthDate=birthDate;
        this.hashpassword=hashpaswword;
        this.salt=salt;
        this.address=address;

    }
    public int getCostumerId(){
        return costumerId;
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
    public int getTelephoneNumber(){
        return telephoneNumber;
    }

    public String getGender(){
        return gender;

    }
    public java.sql.Date getBirthDate(){
        return (java.sql.Date) birthDate;
    }


    public String getPassword(){

        return hashpassword;

    }

    public String getSalt() {
        return salt;
    }

    public String getAddress(){
        return address;
    }

    public static Costumer getInstance(ResultSet rs) throws SQLException {
        int costumerId = rs.getInt("COSTUMER_ID");
        String firstName = rs.getString("FIRST_NAME");
        String lastName = rs.getString("LAST_NAME");
        int telephoneNumber = rs.getInt("TELEPHONE_NUMBER");
        String email = rs.getString("EMAIL");
        String gender = rs.getString("GENDER");
        Date birthDate = rs.getDate("BIRTH_DATE");
        String hashpassword = rs.getString("HASH_PASSWORD");
        String salt = rs.getString("SALT");
        String address = rs.getString("ADDRESS");

        return new Costumer(costumerId, firstName, lastName, telephoneNumber, email, gender, birthDate, hashpassword, salt, address);
    }

}

