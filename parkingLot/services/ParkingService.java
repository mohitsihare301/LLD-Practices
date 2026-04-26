package parkingLot.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import parkingLot.models.ParkingLot;
import parkingLot.models.ParkingSpot;
import parkingLot.models.Vehicle;
import parkingLot.models.ParkingTicket;
import parkingLot.strategies.payment.PaymentStrategy;
import parkingLot.strategies.pricing.PricingStrategy;
import parkingLot.strategies.spotAssignment.SpotAssignmentStrategy;

public class ParkingService{
    private ParkingLot lot;
    private SpotAssignmentStrategy assignmentStrategy;
    private PricingStrategy pricingStrategy;
    private PaymentStrategy paymentStrategy;
    private Map<String,ParkingTicket> tickets = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    public ParkingService(ParkingLot lot, SpotAssignmentStrategy assignmentStrategy, PricingStrategy pricingStrategy, PaymentStrategy paymentStrategy){
        this.lot=lot;
        this.assignmentStrategy = assignmentStrategy;
        this.pricingStrategy = pricingStrategy;
        this.paymentStrategy=paymentStrategy;
    }

    public synchronized Optional<ParkingTicket> parkVehicle(Vehicle vehicle){
        Optional<ParkingSpot> spot = assignmentStrategy.findSpot(vehicle.getType(), lot.getAllLevels());
     
        if(spot.isPresent() && spot.get().occupy(vehicle)){
            ParkingTicket ticket = new ParkingTicket("TKT-"+ counter.getAndIncrement(),spot.get());
            tickets.put(ticket.getId(), ticket);
            System.out.println("Vehicle " + vehicle.getLicensePlate() + " parked at "+ spot.get().getId());
            return Optional.of(ticket);
        }
        return Optional.empty();
    }

    public synchronized void unparkVehicle(String ticketId){
        ParkingTicket ticket = tickets.get(ticketId);
        if(ticket == null){
            System.out.println("Invalid ticket ID: " + ticketId);
        }
        ticket.setExitTime(LocalDateTime.now());
        double parkingFee = pricingStrategy.calculateFee(ticket);
        ticket.setAmount(parkingFee);
        ticket.getSpot().vacate();
        
        boolean success = paymentStrategy.pay(parkingFee);
        if(success){
            System.out.println("  Payment is successful.");
        }
    }
}