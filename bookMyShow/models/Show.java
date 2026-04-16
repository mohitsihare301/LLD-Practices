package bookMyShow.models;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private int id;
    private Movie movie;
    private Screen screen;
    private LocalDateTime startTime;
    private Map<String,ShowSeat> showSeats = new ConcurrentHashMap<>();

    public Show(int id,Movie movie, Screen screen, LocalDateTime startTime){
        this.id=id;
        this.movie=movie;
        this.screen=screen;
        this.startTime=startTime;

        for(Seat seat: screen.getSeats()){
            showSeats.put(seat.getId(),new ShowSeat(seat));
        }
    }

    public int getId(){
        return id;
    }

    public Screen getScreen(){
        return screen;
    }

    public Movie getMovie(){
        return movie;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public ShowSeat getShowSeat(String seatId){
        return showSeats.get(seatId);
    }
    public List<ShowSeat> getAvailableShowSeats(){
        return showSeats.values().stream().filter(showSeat -> showSeat.isAvailable()).toList();
    }

}
