package splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import splitwise.models.Split;
import splitwise.models.User;

public class EqualSplit implements SplitStrategy{
    
    @Override
    public List<Split> split(double amount, List<User>participants){
        if(participants.isEmpty()) throw new IllegalArgumentException("No Participant");
        return participants.stream().map(u -> new Split(u, amount/participants.size())).toList();
    }
}
