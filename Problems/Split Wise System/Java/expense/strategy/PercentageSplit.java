package expense.strategy;

import java.util.ArrayList;
import java.util.List;
import expense.Split;
import user.User;

public class PercentageSplit implements ExpenseStrategy {
    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> users, User paidBy, Double[] splitValues) {
        if (splitValues == null || splitValues.length != users.size()) {
            throw new IllegalArgumentException("Split values must be provided for all users.");
        }

        int numberOfUsers = users.size();
        List<Split> splits = new ArrayList<>();

        for (int i = 0; i < numberOfUsers; i++) {
            double userShare = (splitValues[i] / 100) * totalAmount;
            splits.add(new Split(users.get(i), userShare));
        }

        return splits;
    }
    
}
