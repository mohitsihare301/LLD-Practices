package carRentalSystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Optional;

import carRentalSystem.strategies.pricing.PricingStrategy;
import carRentalSystem.strategies.payment.PaymentStrategy;
import carRentalSystem.enums.BookingStatus;
import carRentalSystem.enums.VehicleStatus;
import carRentalSystem.models.Booking;
import carRentalSystem.models.TimeSlot;
import carRentalSystem.models.User;
import carRentalSystem.models.Vehicle;

public class BookingService {
    private AvailabilityService availabilityService;
    private Map<String, List<Booking>> bookings = new ConcurrentHashMap<>();
    private Map<String, ReentrantLock> vehicleLocks = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);
    private PricingStrategy pricingStrategy;
    private PaymentStrategy paymentStrategy;

    public BookingService(AvailabilityService availabilityService, PricingStrategy pricingStrategy, PaymentStrategy paymentStrategy){
        this.availabilityService = availabilityService;
        this.pricingStrategy = pricingStrategy;
        this.paymentStrategy = paymentStrategy;
    }

    public ReentrantLock getLock(String vehicleId){
        return vehicleLocks.computeIfAbsent(vehicleId, k -> new ReentrantLock());
    }
    public Optional<Booking> createBooking(User user, Vehicle vehicle, TimeSlot timeSlot) throws Exception{
        if(vehicle.getStatus() == VehicleStatus.UNDER_MAINTAINENCE){
            throw new Exception("Vehicle: " + vehicle.getId()+ " is under maintainence.");
        }

        ReentrantLock lock = getLock(vehicle.getId());
        lock.lock();
        try{
            if(!availabilityService.isAvailabe(timeSlot, bookings.get(vehicle.getId()))){
                throw new Exception("Vehicle "+ vehicle.getId()+" is no longer available for the requested time slot.");
            }
            double price = pricingStrategy.calculatePrice(vehicle.getType(), timeSlot);
            Booking booking = new Booking("BKG-" + counter.getAndIncrement(), user, vehicle, timeSlot, price);
            bookings.computeIfAbsent(vehicle.getId(), k-> new ArrayList<>()).add(booking);
            System.out.println("Booking Created: "+ booking.getId() + " User: " + user.getName() + " Vehicle: "+ vehicle.getId() + " Time Slot: " + timeSlot.toString());
            return Optional.of(booking);
        } finally {
            lock.unlock();
        }
    }

    public boolean confirmBooking(Booking booking){
        if(booking.getStatus() != BookingStatus.PENDING){
            return false;
        }

        boolean success = paymentStrategy.pay(booking.getAmount());

        if(success){
            booking.setStatus(BookingStatus.CONFIRMED);
            System.out.println("[" + booking.getId() + "] Booking confirmed");
        } else{
            booking.setStatus(BookingStatus.FAILED);
            bookings.get(booking.getVehicle().getId()).remove(booking);
            System.out.println("Booking failed: " + booking.getId());
        }
        return success;
    }

    public void pickUpVehicle(Booking booking) throws Exception{
        ReentrantLock lock = getLock(booking.getVehicle().getId());
        lock.lock();
        try{
            if(booking.getStatus() != BookingStatus.CONFIRMED){
                throw new Exception("Booking " + booking.getId() + " not confirmed");
            }
            System.out.println("[" + booking.getId() + "] Vehicle picked up.");
            booking.getVehicle().setStatus(VehicleStatus.RENTED);
            booking.setStatus(BookingStatus.ACTIVE);
        } finally {
            lock.unlock();
        }
    }

    public void returnVehicle(Booking booking, LocalDateTime actualReturnTime) throws Exception{
        ReentrantLock lock = getLock(booking.getVehicle().getId());
        lock.lock();
        try {
            if(booking.getStatus() != BookingStatus.ACTIVE){
                throw new Exception("Invalid State");
            }

            booking.setStatus(BookingStatus.COMPLETED);
            booking.getVehicle().setStatus(VehicleStatus.AVAILABLE);
            System.out.println("Booking completed: " + booking.getId());
        } finally {
            lock.unlock();
        }
    }

    public List<Booking> getBookingFor(String vehicleId){
        return bookings.computeIfAbsent(vehicleId, k -> new ArrayList<>());
    }
}
