package carRentalSystem;

import carRentalSystem.models.Car;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import carRentalSystem.enums.VehicleType;
import carRentalSystem.models.Bike;
import carRentalSystem.models.Booking;
import carRentalSystem.models.Location;
import carRentalSystem.models.Store;
import carRentalSystem.models.TimeSlot;
import carRentalSystem.models.User;
import carRentalSystem.models.Vehicle;
import carRentalSystem.services.AvailabilityService;
import carRentalSystem.services.BookingService;
import carRentalSystem.services.RentalService;
import carRentalSystem.strategies.payment.CreditCardPayment;
import carRentalSystem.strategies.pricing.HourlyPricing;

public class CarRentalMain {
    public static void main(String[] args){
           
        Location Goregoan = new Location("Goregoan", 68787, "Mumbai", "Maharashtra");
        Location Bandra = new Location("Bandra", 68787, "Mumbai", "Maharashtra");

        Store S1 = new Store("S1", Goregoan);
        Store S2 = new Store("S2", Bandra);

        Bike Honda = new Bike("V1", "MP075675");
        Bike Pulsar = new Bike("V2", "MP068708");

        Car BMW = new Car("V3", "MP07678");
        Car Swift = new Car("V4", "MP07578");
     
        S1.addVehicle(Swift);
        S1.addVehicle(Honda);

        S2.addVehicle(BMW);
        S2.addVehicle(Pulsar);

        AvailabilityService availabilityService = new AvailabilityService();
        BookingService bookingService = new BookingService(availabilityService, new HourlyPricing(), new CreditCardPayment());

        RentalService rentalService = new RentalService(availabilityService, bookingService);

        TimeSlot timeSlot = new TimeSlot(LocalDateTime.of(2026,5,4,10,0), LocalDateTime.of(2026,5,4,12,0));
        User Mohit = new User("U1","Mohit");
        User Rohit = new User("U2", "Rohit");

        try{
            rentalService.addStore(S1);
            rentalService.addStore(S2);

            System.out.println("Availabe vehicles from 10:00 AM to 12:00 PM on 4/5/2026");
            List<Vehicle> vehicles = rentalService.searchAvailableVehicles("Mumbai", VehicleType.BIKE, timeSlot);
            for(Vehicle vehicle: vehicles){
                System.out.print(" " + vehicle.getId());
            }
            System.out.print("\n\n");

            Optional<Booking> booking = bookingService.createBooking(Rohit, Honda, timeSlot);
            if(booking.isPresent()){
                bookingService.confirmBooking(booking.get());
                bookingService.pickUpVehicle(booking.get());
            }

            System.out.println("Availabe vehicles from 10:00 AM to 12:00 PM on 4/5/2026");
            vehicles = rentalService.searchAvailableVehicles("Mumbai", VehicleType.BIKE, timeSlot);
            for(Vehicle vehicle: vehicles){
                System.out.print(" " + vehicle.getId());
            }





        } catch( Exception e){
            System.out.println(e.getMessage());
        }
    }
}
