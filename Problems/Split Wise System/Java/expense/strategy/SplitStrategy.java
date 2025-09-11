package expense.strategy;

import java.util.List;
import expense.Split;

import user.User;

public class SplitStrategy implements ExpenseStrategy {

    private ExpenseStrategy strategy;

    public SplitStrategy(ExpenseStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> users, User paidBy, Double[] splitValues) {
        return strategy.calculateSplits(totalAmount, users, paidBy, splitValues);
    }
}
