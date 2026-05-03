package meetingRoomScheduler.models;

import java.util.Set;
import meetingRoomScheduler.enums.Amenity;
import meetingRoomScheduler.enums.RoomStatus;

public class Room {
    private String roomId;
    private String name;
    private int capacity;
    private Set<Amenity> amenities;
    private RoomStatus status;

    public Room(String roomId, String name, int capacity, Set<Amenity> amenities){
        this.roomId=roomId;
        this.name=name;
        this.capacity=capacity;
        this.amenities=amenities;
        this.status=RoomStatus.AVAILABLE;
    }

    public String getId(){
        return roomId;
    }

    public String getName(){
        return name;
    }

    public RoomStatus getStatus(){
        return status;
    }

    public void setStatus(RoomStatus status){
        this.status=status;
    }

    public int getCapacity(){
        return capacity;
    }

}
