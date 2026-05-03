package meetingRoomScheduler.service;

import java.util.List;

import meetingRoomScheduler.enums.BookingStatus;
import meetingRoomScheduler.models.Booking;
import meetingRoomScheduler.models.TimeSlot;

public class AvailabilityService{

    public boolean isAvailable(TimeSlot timeSlot, List<Booking> bookings){
        for(Booking booking: bookings){
            if(booking.getStatus() != BookingStatus.CANCELLED){
                if(timeSlot.overlapsWith(booking.getSlot())){
                    return false;
                }
            }
        }
        return true;
    }
};