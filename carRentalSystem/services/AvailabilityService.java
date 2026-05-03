package carRentalSystem.services;

import java.util.List;

import carRentalSystem.models.Booking;
import carRentalSystem.models.TimeSlot;

public class AvailabilityService {
    public boolean isAvailabe(TimeSlot timeSlot, List<Booking> bookings){
        for(Booking booking: bookings){
            if(timeSlot.overlapsWith(booking.getSlot())){
                return false;
            }
        }
        return true;
    }
}
