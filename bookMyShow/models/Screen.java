package bookMyShow.models;

import java.util.List;

public class Screen {
    private int id;
    private String name;
    private List<Seat>seats;

    public Screen(int id,String name,List<Seat>seats){
        this.id=id;
        this.name=name;
        this.seats=seats;
    }

    public int getId(){
        return id;
    }

    public List<Seat> getSeats(){
        return seats; 
    }

    public void addSeat(Seat seat){
        seats.add(seat);
    }

}
