package splitwise.strategy;

import java.util.List;
import splitwise.models.Split;
import splitwise.models.User;

public interface SplitStrategy {
    List<Split> split(double amount, List<User>participants);
}
