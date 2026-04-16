package bookMyShow.pricing;

import bookMyShow.models.ShowSeat;
import bookMyShow.models.Show;

public interface PricingStrategy {
    double calculatePrice(ShowSeat showSeat, Show show);
}
