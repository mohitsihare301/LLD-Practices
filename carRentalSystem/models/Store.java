package carRentalSystem.models;

public class Store {
    private String storeId;
    private InventoryManager inventoryManager;
    private Location location;

    public Store(String storeId, Location location){
        this.storeId = storeId;
        this.inventoryManager = new InventoryManager();
        this.location = location;
    }

    public String getId(){
        return storeId;
    }

    public InventoryManager getInventoryManager(){
        return inventoryManager;
    }

    public void addVehicle(Vehicle vehicle){
        inventoryManager.add(vehicle);
    }

    public Location getLocation(){
        return location;
    }
}
