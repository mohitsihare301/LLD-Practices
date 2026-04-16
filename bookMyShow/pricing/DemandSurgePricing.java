package bookMyShow.pricing;

import bookMyShow.models.*;

public class DemandSurgePricing implements PricingStrategy{
    @Override
    public double calculatePrice(ShowSeat showSeat, Show show){
        int total = show.getScreen().getSeats().size();
        int available  = show.getAvailableShowSeats().size();

        double occupancy = (double)(total-available) / total;
        double multiplier;

        if(occupancy>=0.80) multiplier = 1.2;
        else if(occupancy>=0.90) multiplier = 1.5;
        else multiplier = 1;

        double price = showSeat.getSeat().getBasePrice() * multiplier;
        return price;
    }
}
