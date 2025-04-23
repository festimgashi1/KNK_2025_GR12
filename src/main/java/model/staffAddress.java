package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffAddress {
    private int addressId;
    private String county;
    private String city;
    private String street;
    private int zipCode;

    protected StaffAddress(int addressId, String county, String city, String street, int zipCode){
        this.addressId=addressId;
        this.county=county;
        this.city=city;
        this.street=street;
        this.zipCode=zipCode;
    }

    public static StaffAddress getInstance (ResultSet result) throws SQLException {
        int addressId = result.getInt("addressId");
        String country = result.getString("country");
        String city = result.getString("city");
        String street = result.getString("street");
        int zipCode = result.getInt("zipCode");
        return new StaffAddress(addressId,country,city,street,zipCode);
    }

    public int getAddressId(){
        return addressId;
    }
    public String getCounty(){
        return county;
    }
    public String getCity(){
        return city;
    }
    public String string(){
        return street;
    }
    public int getZipCode(){
        return zipCode;
    }
}
