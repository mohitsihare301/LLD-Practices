package rideSharing.models;

import java.util.concurrent.locks.ReentrantLock;

import rideSharing.enums.DriverStatus;
import rideSharing.enums.VehicleType;

public class Driver extends User{
    private VehicleType vehicleType;
    private DriverStatus status;
    private double rating;

    private ReentrantLock lock = new ReentrantLock(true);

    public Driver(String id, String name, Location location, VehicleType vehicleType, double rating){
        super(id,name,location);
        this.vehicleType=vehicleType;
        this.status=DriverStatus.AVAILABLE;
        this.rating=rating;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }

    public DriverStatus getStatus(){
        return status;
    }

    public void setStatus(DriverStatus status){
        this.status=status;
    }

    public double getRating(){
        return rating;
    }
}
