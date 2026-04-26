package parkingLot.models;

import parkingLot.enums.SpotStatus;
import parkingLot.enums.SpotType;

public class ParkingSpot {
    private String spotId;
    private Vehicle parkedVehicle;
    private SpotType spotType;
    private SpotStatus status;

    public ParkingSpot(String spotId, SpotType spotType){
        this.spotId = spotId;
        this.spotType = spotType;
        this.status = SpotStatus.AVAILABLE;
    }

    public String getId(){
        return spotId;
    }

    public SpotType getType(){
        return spotType;
    }

    public synchronized boolean isAvailable(){
        return status == SpotStatus.AVAILABLE;
    }

    public synchronized boolean occupy(Vehicle vehicle){
        if(!isAvailable()) return false;
        this.status = SpotStatus.OCCUPIED;
        this.parkedVehicle = vehicle;
        return true; 
    }

    public synchronized void vacate(){
        this.status = SpotStatus.AVAILABLE;
        this.parkedVehicle = null;
    }



}
