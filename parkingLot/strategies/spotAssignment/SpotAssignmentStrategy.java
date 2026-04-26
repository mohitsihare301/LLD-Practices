package parkingLot.strategies.spotAssignment;

import parkingLot.enums.VehicleType;
import parkingLot.models.ParkingSpot;
import parkingLot.models.Level;

import java.util.List;
import java.util.Optional;

public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> findSpot(VehicleType vehicleType, List<Level>levels);
}
