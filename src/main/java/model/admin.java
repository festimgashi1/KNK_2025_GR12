package model;

import java.sql.Date;

public class admin {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashpass;
    private String salt;
    private int addressId;

    protected admin(int id, String firstName,String lastName, String email, String hashpass, String salt, int addressId){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.hashpass=hashpass;
        this.salt=salt;
        this.addressId=addressId;
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
