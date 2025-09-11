package expense.strategy;

import java.util.List;

import expense.Split;
import user.User;

public interface ExpenseStrategy {
    List<Split> calculateSplits(double totalAmount, List<User> users, User paidBy, Double[] splitValues);
}
