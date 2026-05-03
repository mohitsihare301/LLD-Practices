package carRentalSystem.strategies.pricing;

import java.util.Map;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

import carRentalSystem.enums.VehicleType;
import carRentalSystem.models.TimeSlot;

public class HourlyPricing implements PricingStrategy{
    private Map<VehicleType, Double> rates = new HashMap<>();

    public HourlyPricing(){
        rates.put(VehicleType.BIKE, 1.0);
        rates.put(VehicleType.CAR, 3.0);
        rates.put(VehicleType.SUV, 5.0);
    }

    @Override
    public double calculatePrice(VehicleType vehicleType, TimeSlot timeSlot){
        Duration duration = timeSlot.getDuration();
        long hours = duration.toHours();
        if(duration.toMinutes() % 60 > 0) hours++;
        return hours * rates.get(vehicleType);
    }
}
