package parkingLot.models;

import parkingLot.enums.VehicleType;

public class MotorCycle  extends Vehicle{
    public MotorCycle(String licensePlate){
        super(licensePlate, VehicleType.MOTORCYCLE);
    }
}
