package makeMyTrip.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import makeMyTrip.enums.BookingStatus;
import makeMyTrip.enums.PaymentMode;
import makeMyTrip.enums.PaymentStatus;
import makeMyTrip.models.Passenger;
import makeMyTrip.models.User;
import makeMyTrip.payment.PaymentProcessor;
import makeMyTrip.models.Flight;
import makeMyTrip.models.Booking;
import makeMyTrip.models.Payment;

import makeMyTrip.models.Seat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private static BookingService instance;
    private Map<String,Booking> bookings = new ConcurrentHashMap<>();
    private PaymentProcessor paymentProcessor = new PaymentProcessor();
    private AtomicInteger counter = new AtomicInteger(1);
    private BookingService(){}

    public static synchronized BookingService getInstance(){
        if(instance==null){
            instance = new BookingService();
        }
        return instance;
    }

    public Booking getBooking(String bookingId){
        return bookings.get(bookingId);
    }

    public Booking createBooking(User user, Flight flight, List<Passenger> passengers, List<String>seatIds){
        if(passengers.size()!=seatIds.size()){
            System.out.println("Passenges count must match with seats count.");
            return null;
        }

        
        if(!flight.reserveSeats(seatIds)){
            System.out.println("["+ user.getName()+ "] Seat reservation failed - already occupied");
            return null;
        }  

        List<Seat>bookedSeats = new ArrayList<>();
        for(String seatId: seatIds) bookedSeats.add(flight.getSeat(seatId));
        
        Booking booking = new Booking("BKG-"+counter.getAndIncrement(), user, flight, passengers, bookedSeats);
        System.out.println("["+user.getName()+"] Booking successfully created. Seats locked -> "+ seatIds);
        bookings.put(booking.getId(), booking);
        return booking;

    }

    public boolean confirmBooking(Booking booking, PaymentMode mode){
        Payment payment= new Payment("PAY-"+counter.getAndIncrement(), mode, booking.getAmount());
        booking.setPayment(payment);

        boolean success = paymentProcessor.process (payment);
        if(success){
            booking.setStatus(BookingStatus.CONFIRMED);
            booking.geFlight().confirmSeats(booking.getSeatIds());
            System.out.println("["+booking.getUser().getName()+ "] Booking CONFIRMED "+ booking.getId()+" | Rs "+ booking.getAmount() );    
        }else{
            booking.setStatus(BookingStatus.FAILED);
            booking.geFlight().releaseSeats(booking.getSeatIds());
            System.out.println("["+booking.getUser().getName()+"] Booking FAILED - Seats released");
        }
        return success;

    }
}
