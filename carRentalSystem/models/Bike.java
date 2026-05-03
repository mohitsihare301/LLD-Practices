package carRentalSystem.models;

import carRentalSystem.enums.VehicleType;

public class Bike extends Vehicle{
    public Bike(String id, String licensePlate){
        super(id, licensePlate, VehicleType.BIKE);
    }
}
