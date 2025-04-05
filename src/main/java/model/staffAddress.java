package model;

public class staffAddress {
    private int addressId;
    private String county;
    private String city;
    private String street;
    private int zipCode;

    protected staffAddress(int addressId, String county, String city, String street, int zipCode){
        this.addressId=addressId;
        this.county=county;
        this.city=city;
        this.street=street;
        this.zipCode=zipCode;
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
