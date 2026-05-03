package meetingRoomScheduler.service;

import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import meetingRoomScheduler.models.Booking;
import meetingRoomScheduler.models.Room;
import meetingRoomScheduler.models.TimeSlot;
import meetingRoomScheduler.models.User;

public class BookingService {
    private AvailabilityService availabilityService;
    private Map<String, List<Booking>> bookings = new ConcurrentHashMap<>();
    private Map<String,ReentrantLock> roomLocks = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    public BookingService(AvailabilityService availabilityService){
        this.availabilityService=availabilityService;
    }

    public Optional<Booking> bookRoom(User user, Room room, TimeSlot timeSlot) throws Exception{
        ReentrantLock lock = roomLocks.computeIfAbsent(room.getId(), k -> new ReentrantLock());
        lock.lock();
        try{
            if(!availabilityService.isAvailable(timeSlot, getBookingFor(room.getId()))){
                throw new Exception("Room is no longer available for the requested time slot.");
            }

            Booking booking = new Booking("BKG-" + counter.getAndIncrement(), user, room, timeSlot);
            bookings.computeIfAbsent(room.getId(), k -> new ArrayList<>()).add(booking);
            return Optional.of(booking);
        } finally {
            lock.unlock();
        }
    }

    public List<Booking> getBookingFor(String roomId){
        return bookings.computeIfAbsent(roomId, k -> new ArrayList<>());
    }
}
