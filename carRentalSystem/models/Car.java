package carRentalSystem.models;

import carRentalSystem.enums.VehicleType;

public class Car extends Vehicle{
    public Car(String id, String licensePlate){
        super(id, licensePlate, VehicleType.CAR);
    }
}
