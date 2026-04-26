package parkingLot.strategies.pricing;

import parkingLot.models.ParkingTicket;

public interface PricingStrategy {
    double calculateFee(ParkingTicket ticket);
}
