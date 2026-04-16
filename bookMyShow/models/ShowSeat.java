package bookMyShow.models;

import java.util.concurrent.locks.ReentrantLock;
import bookMyShow.enums.SeatStatus;

public class ShowSeat {
    private Seat seat;
    private SeatStatus status;
    private ReentrantLock lock = new ReentrantLock(true);

    public ShowSeat(Seat seat){
        this.seat=seat;
        this.status = SeatStatus.AVAILABLE;
    }

    public boolean book(){
        lock.lock();
        try{
            if(status!=SeatStatus.AVAILABLE){
                return false;
            }
            this.status=SeatStatus.BOOKED;
            return true;
        }finally{
            lock.unlock();
        }
    }

    public void release(){
        lock.lock();
        try{
            this.status=SeatStatus.AVAILABLE;
        }finally{
            lock.unlock();
        }
    }

    public String getId(){
        return seat.getId();
    }
    public boolean isAvailable(){
        return status == SeatStatus.AVAILABLE;
    }

    public SeatStatus getStatus(){
        return status;
    }

    public Seat getSeat(){
        return seat;
    }

}
