package bookMyShow.pricing;

import bookMyShow.models.ShowSeat;
import bookMyShow.models.Show;

public class BasePricing implements PricingStrategy{
    
    @Override
    public double calculatePrice(ShowSeat showSeat, Show show){
        return showSeat.getSeat().getBasePrice();
    }
}
