package parkingLot.models;

import java.util.ArrayList;
import java.util.List;

import parkingLot.enums.SpotType;

public class Level {
    private String levelId;
    private List<ParkingSpot> spots;

    public Level(String levelId, int numSpots){
        this.levelId= levelId;
        spots = new ArrayList<>(numSpots);
        for(int i=1;i<=numSpots;i++){
            SpotType spotType = (i<=numSpots/3) ? SpotType.SMALL : (i>numSpots/3 && i<=(2*numSpots)/3 ? SpotType.MEDIUM : SpotType.LARGE);
            spots.add(new ParkingSpot("S-"+i, spotType));
        }
    }

    public String getId(){
        return levelId;
    }

    public List<ParkingSpot> getAvailableSpots(SpotType spotType){
        return spots.stream().filter( spot -> spot.isAvailable() && spot.getType() == spotType ).toList();
    }

    public List<ParkingSpot> getSpots(){
        return spots;
    }


}
