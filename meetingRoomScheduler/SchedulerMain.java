package meetingRoomScheduler;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Set;

import meetingRoomScheduler.enums.Amenity;
import meetingRoomScheduler.models.TimeSlot;
import meetingRoomScheduler.models.User;
import meetingRoomScheduler.models.Room;
import meetingRoomScheduler.service.AvailabilityService;
import meetingRoomScheduler.service.BookingService;
import meetingRoomScheduler.service.RoomService;

public class SchedulerMain {
    public static void main(String[] args){
        AvailabilityService availabilityService = new AvailabilityService();
        BookingService bookingService = new BookingService(availabilityService);
        RoomService roomService = new RoomService(availabilityService, bookingService);
         
        User Mohit = new User("U1","Mohit");
        User Himanshu = new User("U2", "Himanshu");

        Room room1 = roomService.createRoom("ROOM-1", "Board Room", 5, Set.of(Amenity.PROJECTOR, Amenity.WHITEBOARD, Amenity.WHITEBOARD_MARKERS));
        Room room2 = roomService.createRoom("ROOM-2", "Conference Room", 10, Set.of(Amenity.PROJECTOR, Amenity.WHITEBOARD, Amenity.WHITEBOARD_MARKERS));

        TimeSlot timeSlot = new TimeSlot(LocalDateTime.of(2026,5,4,8,0), LocalDateTime.of(2026,5,4,10,30));
        List<Room>rooms = roomService.searchAvailableRooms(timeSlot);

        System.out.println("Available rooms from 8:00 AM to 10:30 AM on 4/5/2026");
        for(Room room: rooms){
            System.out.println("Room ID: "+ room.getId());
        }

        try{
            bookingService.bookRoom(Himanshu, room1, timeSlot);
        } catch ( Exception e){
            System.out.println("Booking failed: " + e.getMessage());
        }

        rooms = roomService.searchAvailableRooms(timeSlot);
        System.out.println("Available rooms from 8:00 AM to 10:30 AM on 4/5/2026");
        for(Room room: rooms){
            System.out.println("Room ID: "+ room.getId());
        }
    }
}
