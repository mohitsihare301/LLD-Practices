package parkingLot.models;

import parkingLot.enums.VehicleType;

public abstract class Vehicle {
    private String licensePlate;
    private VehicleType type;

    public Vehicle(String licensePlate, VehicleType type){
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public VehicleType getType(){
        return type;
    }
}
