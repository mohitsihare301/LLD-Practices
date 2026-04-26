package parkingLot.strategies.pricing;

import java.util.Map;

import bookMyShow.enums.SeatType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import parkingLot.models.ParkingTicket;
import parkingLot.enums.SpotType;

public class HourlyPricingStrategy implements PricingStrategy{
    private Map< SpotType, Double> rates = new HashMap<>();

    public HourlyPricingStrategy(){
        rates.put(SpotType.SMALL , 1.0);
        rates.put(SpotType.MEDIUM , 3.0);
        rates.put(SpotType.LARGE , 5.0);

    }
    @Override
    public double calculateFee(ParkingTicket ticket){
        Duration duration = Duration.between(ticket.getEntryTime(), LocalDateTime.now());
        long hours = duration.toHours();
        if(((long) duration.toMinutes()) % 60 > 0) hours++;
        SpotType spotType = ticket.getSpot().getType();
        return hours * rates.get(spotType);
    }
}
