package model;
import java.nio.file.attribute.UserPrincipal;
import java.sql.Date;

public class user {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int telephoneNumber;
    private String gender;
    private Date birthDate;
    private String password;
    private String address;
    private String role;

    protected user(int id ,String firstName, String lastName,int telephoneNumber, String email,String gender,Date birthDate,String paswword,String address , String role){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.telephoneNumber=telephoneNumber;
        this.gender=gender;
        this.birthDate=birthDate;
        this.password=paswword;
        this.address=address;
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

         return password;

    }
    public String getAddress(){
        return address;
    }
    public String getRole(){
        return role;
    }


}

