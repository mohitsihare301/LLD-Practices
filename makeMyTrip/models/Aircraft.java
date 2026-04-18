package makeMyTrip.models;

import java.util.List;

public class Aircraft {
    private String id;
    private Airline airline;
    private List<Seat> seats;

    public Aircraft(String id,Airline airline, List<Seat> seats){
        this.id=id;
        this.airline = airline;
        this.seats = seats;
    }

    public String getId(){
        return id;
    }

    public void addSeat(Seat seat){
        seats.add(seat);
    }

    public List<Seat> getSeats(){
        return seats;
    }
}
