package parkingLot.models;

import java.time.LocalDateTime;

public class ParkingTicket {
    private String ticketId;
    private ParkingSpot spot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount;

    public ParkingTicket(String ticketId, ParkingSpot spot){
        this.ticketId = ticketId;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public String getId(){
        return ticketId;
    }

    public LocalDateTime getEntryTime(){
        return entryTime;
    }

    public LocalDateTime getExitTime(){
        return exitTime;
    }
    public void setExitTime(LocalDateTime exitTime){
        this.exitTime = exitTime;
    }

    public ParkingSpot getSpot(){
        return spot;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}
