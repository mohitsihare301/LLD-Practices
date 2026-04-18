package makeMyTrip.models;

import java.util.List;

public class User {
    private String id;
    private String name;
    private String email;
    private List<Booking>bookings;

    public User(String id, String name, String email){
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public List<Booking> getAllBookings(){
        return bookings;
    }
}
