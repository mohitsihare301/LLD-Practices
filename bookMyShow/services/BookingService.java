package bookMyShow.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import bookMyShow.enums.BookingStatus;
import bookMyShow.models.Booking;

import bookMyShow.models.Show;
import bookMyShow.models.ShowSeat;
import bookMyShow.models.User;
import bookMyShow.payment.PaymentStrategy;
import bookMyShow.pricing.PricingStrategy;
import java.util.Collection;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private static BookingService instance;
    private Map<String,Booking>bookings = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    private BookingService(){

    };

    public static synchronized BookingService getInstance(){
        if(instance==null){
            instance = new BookingService();
        }
        return instance;
    }

    public Booking bookSeats(
        User user,
        Show show,
        List<String>seatIds,
        PricingStrategy pricing,
        PaymentStrategy payment
    ){
        List<ShowSeat>bookedSeats = new ArrayList<>();
        for(String seatId: seatIds){
            ShowSeat showSeat = show.getShowSeat(seatId);
            if(showSeat==null || !showSeat.book() ){
                bookedSeats.forEach(seat -> seat.release());
                System.out.println("[FAILED] Seat " + seatId + " already occupied.");
                return makeBooking(user, show,List.of(), 0, BookingStatus.FAILED);
            }
            bookedSeats.add(showSeat);
        }

        double totalAmount = bookedSeats.stream().mapToDouble(seat -> pricing.calculatePrice(seat, show)).sum();

        boolean paid = payment.pay(totalAmount);
        if(!paid){
            bookedSeats.forEach(seat->seat.release());
            System.out.println("[FAILED] payment failed");
            return makeBooking(user, show,List.of(),0, BookingStatus.FAILED);
        }

        Booking booking = makeBooking(user, show, bookedSeats, totalAmount,BookingStatus.CONFIRMED);
        bookings.put(booking.getId(), booking);
        return booking;
    }
    public Booking makeBooking(User user, Show show, List<ShowSeat>seats, double amount, BookingStatus status){
        Booking booking = new Booking("BKG-" + counter.getAndIncrement(), user, show, seats, status, amount);
        return booking;
    }

    public Booking getBooking(String id){
        return bookings.get(id);
    }

    public Collection<Booking> getAllBookings(){
        return bookings.values();
    }
}
