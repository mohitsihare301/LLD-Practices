package splitwise.models;

import java.util.List;

public class Expense {
    private String id;
    private User paidBy;
    private double amount;
    private List<Split> splits;

    public Expense(String id,User paidBy, double amount, List<Split>splits){
        this.id=id;
        this.paidBy=paidBy;
        this.amount=amount;
        this.splits=splits;
    }

    public String getId(){
        return id;

    }

    public List<Split> getSplits(){
        return splits;
    }
}
