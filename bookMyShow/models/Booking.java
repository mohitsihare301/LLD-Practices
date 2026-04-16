package bookMyShow.models;

import bookMyShow.enums.BookingStatus;
import java.util.List;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private List<ShowSeat>seats;
    private BookingStatus status;
    private double totalAmount;

    public Booking(String id, User user, Show show, List<ShowSeat>seats, BookingStatus status, double totalAmount){
        this.id=id;
        this.user=user;
        this.show=show;
        this.seats=seats;
        this.status=status;
        this.totalAmount=totalAmount;
    }

    public String getId(){
        return id;
    }

    public BookingStatus getBookingStatus(){
        return status;
    }

    public double getBookingAmount(){
        return totalAmount;
    }

}
