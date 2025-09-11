package expense;

import java.util.ArrayList;
import java.util.List;

import expense.strategy.SplitStrategy;
import user.User;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private List<Split> splits;
    private User paidBy;
    private SplitStrategy splitStrategy;

    public Expense(int id, String description, double amount, User paidBy, SplitStrategy splitStrategy) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitStrategy = splitStrategy;
        this.splits = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public SplitStrategy getSplitStrategy() {
        return splitStrategy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<User> users, Double[] splitValues) {
        this.splits = this.splitStrategy.calculateSplits(amount, users, paidBy, splitValues);
    }
}
