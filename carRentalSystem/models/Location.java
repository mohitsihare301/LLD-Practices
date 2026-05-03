package carRentalSystem.models;

public class Location {

    private String address;
    private int pincode;
    private String city;
    private String state;

    public Location(String addresss, int pincode, String city, String state){
        this.address = addresss;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
    }

    public String getCity(){
        return city;
    }
}
