package meetingRoomScheduler.models;

import java.time.LocalDateTime;

import meetingRoomScheduler.enums.BookingStatus;

public class Booking {
    private String bookingId;
    private User user;
    private Room room;
    private TimeSlot timeSlot;
    private BookingStatus status;
    private LocalDateTime bookingTime;

    public Booking(String booknigId, User user, Room room, TimeSlot timeSlot){
        this.bookingId=booknigId;
        this.user=user;
        this.room=room;
        this.timeSlot=timeSlot;
        this.status=BookingStatus.CONFIRMED;
        this.bookingTime=LocalDateTime.now();
    }

    public String getId(){
        return bookingId;
    }

    public User getUser(){
        return user;
    }

    public Room getRoom(){
        return room;
    }

    public BookingStatus getStatus(){
        return status;
    }

    public void setStatus(BookingStatus status){
        this.status=status;
    }

    public TimeSlot getSlot(){
        return timeSlot;
    }
}
