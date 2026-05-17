package splitwise.service;

import java.util.List;

import splitwise.enums.SplitType;
import splitwise.models.Expense;
import splitwise.models.Split;
import splitwise.models.User;
import splitwise.strategy.SplitStrategy;
import splitwise.strategy.SplitStrategyFactory;

public class ExpenseService {
    private BalanceService balanceService;
    private SplitStrategyFactory strategyFactory;

    public ExpenseService(BalanceService balanceService) {
        this.balanceService = balanceService;
        this.strategyFactory = new SplitStrategyFactory();
    }

    public Expense createExpense(User paidBy, double amount,List<User> participants, SplitType splitType, Object config){
        SplitStrategy strategy = strategyFactory.getSplitStrategy(splitType, config);
        List<Split> splits = strategy.split(amount, participants);
        for()
    }
}
