package parkingLot.models;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    public static ParkingLot instance;
    private List<Level>levels;

    private ParkingLot() {
        levels = new ArrayList<>();
    };

    public static synchronized ParkingLot getInstance(){
        if(instance==null){
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addLevel(Level level){
        levels.add(level);
    }

    public List<Level> getAllLevels(){
        return levels;
    }
}
