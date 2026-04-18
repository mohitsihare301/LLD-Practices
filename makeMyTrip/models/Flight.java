package makeMyTrip.models;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import makeMyTrip.enums.SeatClass;
import makeMyTrip.models.*;

public class Flight {
    private String id;
    private Aircraft aircraft;
    private Map<String,Seat> seats = new ConcurrentHashMap<>();
    private Airport source;
    private Airport destination;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    public Flight(String id, Aircraft aircraft, Airport source, Airport destination, LocalDateTime arrivalTime, LocalDateTime departureTime){
        this.id=id;
        this.aircraft = aircraft;
        this.source=source;
        this.destination=destination;
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;

        for(Seat seat: aircraft.getSeats()){
            seats.put(seat.getId(), seat);
        }
    }

    public String getId(){
        return id;
    }

    public Seat getSeat(String seatId){
        return seats.get(seatId);
    }

    public Airport getSourceAirport(){
        return source;
    }

    public Airport getDestinationAirport(){
        return destination;
    }

    public LocalDateTime getArrivalTime(){
        return arrivalTime;
    }
    public LocalDateTime getDepartureTime(){
        return departureTime;
    }

    public boolean reserveSeats(List<String>seatIds){
        List<Seat>blockedSeats = new ArrayList<>();
        for(String seatId: seatIds){
            Seat seat = seats.get(seatId);
            if(seat==null || !seat.block()){
                blockedSeats.forEach(s->s.release());
                System.out.println(" Seat with ID: "+ seat.getId()+ " already occupied.");
                return false;
            }
            blockedSeats.add(seat);
        }
        return true;
    }

    public void confirmSeats(List<String>seatIds){
        for(String seatId: seatIds){
            Seat seat = seats.get(seatId);
            if(seat!=null){
                seat.book();
            }
        }
    }

    public void releaseSeats(List<String>seatIds){
        for(String seatId: seatIds){
            Seat seat = seats.get(seatId);
            if(seat!=null){
                seat.release();
            }
        }
    }

    public List<Seat> getAvailableSeats(SeatClass seatClass){
        return seats.values().stream().filter(seat -> seat.isAvailable() && seat.getSeatClass()==seatClass).toList();
    }

    public long getAvailableSeatsCount(SeatClass seatClass){
        return seats.values().stream().filter(seat -> seat.isAvailable() && seat.getSeatClass()==seatClass).count();
    }

}
