package rideSharing.models;

public abstract class User {
    private String userId;
    private String name;
    private Location location;

    public User(String userId, String name, Location location){
        this.userId=userId;
        this.name=name;
        this.location=location;
    }

    public String getId(){
        return userId;
    }

    public String getName(){
        return name;
    }

    public Location getCurrentLocation(){
        return location;
    }

}
