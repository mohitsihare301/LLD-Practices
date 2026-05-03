package carRentalSystem.models;

import java.time.LocalDateTime;

import carRentalSystem.enums.BookingStatus;

public class Booking {
    private String id;
    private Vehicle vehicle;
    private User user;
    private BookingStatus status;
    private TimeSlot timeSlot;
    private double amount;

    public Booking(String id, User user, Vehicle vehicle, TimeSlot timeSlot, double amount){
        this.id=id;
        this.vehicle=vehicle;
        this.user=user;
        this.status=BookingStatus.PENDING;
        this.timeSlot=timeSlot;
        this.amount=amount;
    }

    public String getId(){
        return id;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount=amount;
    }

    public TimeSlot getSlot(){
        return timeSlot;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public User getUser(){
        return user;
    }

    public BookingStatus getStatus(){
        return status;
    }

    public void setStatus(BookingStatus status){
        this.status = status;
    }

}
