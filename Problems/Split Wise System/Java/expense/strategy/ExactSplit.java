package expense.strategy;

import java.util.ArrayList;
import java.util.List;
import expense.Split;
import user.User;

public class ExactSplit implements ExpenseStrategy {
    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> users, User paidBy, Double[] splitValues) {
        if (splitValues == null || splitValues.length != users.size()) {
            throw new IllegalArgumentException("Split values must be provided for all users.");
        }

        List<Split> splits = new ArrayList<>();
        double totalSplit = 0;

        for (double value : splitValues) {
            totalSplit += value;
        }

        if (totalSplit != totalAmount) {
            throw new IllegalArgumentException("The sum of split values must equal the total amount.");
        }

        for (int i = 0; i < users.size(); i++) {
            splits.add(new Split(users.get(i), splitValues[i]));
        }

        return splits;
    }
    
}
