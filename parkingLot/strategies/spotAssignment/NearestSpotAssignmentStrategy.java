package parkingLot.strategies.spotAssignment;

import parkingLot.enums.SpotType;
import parkingLot.enums.VehicleType;
import parkingLot.models.ParkingSpot;
import parkingLot.models.Level;
import java.util.List;
import java.util.Optional;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(VehicleType vehicleType, List<Level> levels){
        for(Level level: levels){
            for(ParkingSpot spot: level.getSpots()){
                if(spot.isAvailable() && canFit(vehicleType, spot.getType())){
                    return Optional.of(spot);
                }
            }
        }
        return Optional.empty();
    }

    private boolean canFit(VehicleType vehicleType , SpotType spotType){
        switch(vehicleType){
            case VehicleType.MOTORCYCLE:
                return true;
            case VehicleType.CAR:
                if(spotType == SpotType.SMALL){
                    return false;
                }
                return true;
            case VehicleType.BUS:
                if(spotType == SpotType.SMALL || spotType == SpotType.MEDIUM){
                    return false;
                }
                return true;
            case VehicleType.TRUCK:
                if(spotType == SpotType.SMALL || spotType == SpotType.MEDIUM){
                    return false;
                }
                return true;
            default: 
                return false;
        }
    }
}
