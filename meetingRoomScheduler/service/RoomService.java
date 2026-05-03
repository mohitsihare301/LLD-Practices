package meetingRoomScheduler.service;

import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import meetingRoomScheduler.enums.Amenity;
import meetingRoomScheduler.models.Room;
import meetingRoomScheduler.models.TimeSlot;

public class RoomService {
    private AvailabilityService availabilityService;
    private BookingService bookingService;
    private Map<String,Room> rooms = new ConcurrentHashMap<>();

    public RoomService(AvailabilityService availabilityService, BookingService bookingService){
        this.availabilityService=availabilityService;
        this.bookingService=bookingService;
    }
    
    public Room createRoom(String roomId, String name, int capacity, Set<Amenity> amenities){
        Room room = new Room(roomId, name, capacity, amenities);
        rooms.put(roomId, room);
        System.out.println("Room Created: "+ roomId);
        return room;
    }

    public List<Room> searchAvailableRooms(TimeSlot timeSlot){
        return rooms.values().stream().filter(room -> availabilityService.isAvailable(timeSlot, bookingService.getBookingFor(room.getId()))).toList();
    }
}
