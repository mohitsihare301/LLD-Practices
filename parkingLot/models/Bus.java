package parkingLot.models;

import parkingLot.enums.VehicleType;

public class Bus extends Vehicle{
    public Bus(String licensePlate){
        super(licensePlate, VehicleType.BUS);
    }
}
