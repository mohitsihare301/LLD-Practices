package splitwise.strategy;

import java.util.Map;
import java.util.List;
import splitwise.models.User;
import splitwise.models.Split;

public class ExactSplit implements SplitStrategy{
    private Map<String,Double> exactAmounts;

    public ExactSplit(Map<String,Double> exactAmounts){
        this.exactAmounts = exactAmounts;
    }

    @Override
    public List<Split> split(double amount, List<User>participants){
        double sum = exactAmounts.values().stream().mapToDouble(Double::doubleValue).sum();
        if(Math.abs(sum-amount) > 0.01){
            throw new IllegalArgumentException("Exact amounts must equal total amount");
        }
        return participants.stream().map(user -> new Split(user,exactAmounts.get(user.getId()))).toList();
    }
}
