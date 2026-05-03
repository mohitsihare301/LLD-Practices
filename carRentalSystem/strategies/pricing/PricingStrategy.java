package carRentalSystem.strategies.pricing;

import java.time.LocalDateTime;
import carRentalSystem.enums.VehicleType;
import carRentalSystem.models.TimeSlot;

public interface PricingStrategy {
    double calculatePrice(VehicleType vehicleType, TimeSlot timeSlot); 
}
