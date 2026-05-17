package splitwise.strategy;

import java.util.Map;
import java.util.List;
import splitwise.models.User;
import splitwise.models.Split;

public class PercentageSplit implements SplitStrategy{
    private Map<String,Double>percentages;

    public PercentageSplit(Map<String,Double>percentages){
        this.percentages=percentages;
    }

    @Override
    public List<Split> split(double amount, List<User>participants){
        double sum = percentages.values().stream().mapToDouble(Double::doubleValue).sum();
        if(Math.abs(sum-100)>0.01){
            throw new IllegalArgumentException("Percentages must sum to 100");
        }
        return participants.stream().map(
            user -> new Split(user, (percentages.get(user.getId())/100) * amount)
        ).toList();
    }
}
