package carRentalSystem.models;

import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import carRentalSystem.enums.VehicleType;

public class InventoryManager {
    private Map<String, Vehicle> vehicles = new ConcurrentHashMap<>();

    public void add(Vehicle vehicle){
        vehicles.put(vehicle.getId(), vehicle);
    }

    public void remove(String vehicleId){
        vehicles.remove(vehicleId);
    }

    public Vehicle getVehicle(String vehicleId){
        return vehicles.get(vehicleId);
    }

    public List<Vehicle> getVehiclesOf(VehicleType vehicleType){
        return vehicles.values().stream().filter(vehicle -> vehicle.getType() == vehicleType).toList();
    }
}
