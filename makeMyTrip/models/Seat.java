package makeMyTrip.models;

import java.util.concurrent.locks.ReentrantLock;

import makeMyTrip.enums.SeatClass;
import makeMyTrip.enums.SeatStatus;

public class Seat{
    private String seatId;
    private SeatClass seatClass;
    private SeatStatus status;
    private double price;
    private ReentrantLock lock = new ReentrantLock();

    public Seat(String seatId,SeatClass seatClass , SeatStatus status, double price){
        this.seatId=seatId;
        this.seatClass=seatClass;
        this.status=status;
        this.price=price;
    }
 
    public String getId(){
        return seatId;
    }

    public double getBasePrice(){
        return price;
    }

    public SeatClass getSeatClass(){
        return seatClass;
    }

    public boolean block(){
        lock.lock();
        try{
            if(status!=SeatStatus.AVAILABLE){
                return false;
            }
            status= SeatStatus.LOCKED;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean book(){
        lock.lock();
        try{
            if(status!=SeatStatus.LOCKED){
                return false;
            }
            status=SeatStatus.BOOKED;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void release(){
        lock.lock();
        try{
            status = SeatStatus.AVAILABLE;
        } finally {
            lock.unlock();
        }
    }

    public boolean isAvailable(){
        return status == SeatStatus.AVAILABLE;
    }
}