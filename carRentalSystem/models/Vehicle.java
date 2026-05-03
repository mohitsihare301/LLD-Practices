package carRentalSystem.models;

import carRentalSystem.enums.VehicleStatus;
import carRentalSystem.enums.VehicleType;

public abstract class Vehicle {
    private String id;
    private String licensePlate;
    private VehicleType vehicleType;
    private VehicleStatus status;

    public Vehicle(String id, String licensePlate, VehicleType vehicleType){
        this.id = id;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getId(){
        return id;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public VehicleType getType(){
        return vehicleType;
    }

    public VehicleStatus getStatus(){
        return status;
    }

    public void setStatus(VehicleStatus status){
        this.status=status;
    }
}
