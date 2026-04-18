package makeMyTrip.models;

import java.time.LocalDateTime;
import java.util.List;

import makeMyTrip.enums.BookingStatus;

public class Booking {
    private String id;
    private User user;
    private Flight flight;
    private List<Passenger> passengers;
    private List<Seat>seats;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private double totalAmount;
    private Payment payment;

    public Booking(String id, User user, Flight flight, List<Passenger> passengers, List<Seat>seats){
        this.id=id;
        this.user=user;
        this.flight=flight;
        this.passengers=passengers;
        this.seats=seats;
        this.bookingTime=LocalDateTime.now();
        this.totalAmount=seats.stream().mapToDouble(seat-> seat.getBasePrice()).sum();
        this.status=BookingStatus.PENDING;
    }

    public String getId(){
        return id;
    }

    public User getUser(){
        return user;
    }

    public Flight geFlight(){
        return flight;
    }

    public List<String> getSeatIds(){
        return seats.stream().map(s-> s.getId()).toList();
    }
    
    public BookingStatus getStatus(){
        return status;
    }

    public void setStatus(BookingStatus status){
        this.status=status;
    }

    public double getAmount(){
        return totalAmount;
    }

    public void setPayment(Payment payment){
        this.payment=payment;
    }

    public Payment getPayment(){
        return payment;
    }

}
