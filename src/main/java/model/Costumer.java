package model;
import java.sql.Date;

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


}

