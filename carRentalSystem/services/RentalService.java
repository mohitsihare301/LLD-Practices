package carRentalSystem.services;

import java.util.ArrayList;
import java.util.List;

import carRentalSystem.models.Booking;
import carRentalSystem.models.Store;
import carRentalSystem.models.TimeSlot;
import carRentalSystem.enums.VehicleType;
import carRentalSystem.models.Vehicle;

public class RentalService {
    private AvailabilityService availabilityService;
    private BookingService bookingService;
    private List<Store>stores;

    public RentalService(AvailabilityService availabilityService, BookingService bookingService){
        this.availabilityService = availabilityService;
        this.bookingService = bookingService;
        this.stores = new ArrayList<>();
        
    }

    public void addStore(Store store) throws Exception {
        if(store == null){
            throw new Exception("No store found.");
        }
        stores.add(store);
        System.out.println("Store Created: " + store.getId());
    }

    public List<Vehicle> searchAvailableVehicles(String city, VehicleType vehicleType, TimeSlot timeSlot){
        List<Vehicle> availableVehicles = new ArrayList<>();
        for(Store store: stores){
            if(store.getLocation().getCity() == city){
                for(Vehicle vehicle: store.getInventoryManager().getVehiclesOf(vehicleType)){
                   if(availabilityService.isAvailabe(timeSlot, bookingService.getBookingFor(vehicle.getId()))){
                        availableVehicles.add(vehicle);
                   }
                }
            }
        }
        return availableVehicles;
    }



  




}
