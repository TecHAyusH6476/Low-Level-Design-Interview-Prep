package expense.strategy;

import java.util.ArrayList;
import java.util.List;

import expense.Split;
import user.User;

public class EqualSplit implements ExpenseStrategy {
    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> users, User paidBy, Double[] splitValues) {
        int numberOfUsers = users.size();
        double equalShare = totalAmount / numberOfUsers;
        List<Split> splits = new ArrayList<>();

        for (User user : users) {
            splits.add(new Split(user, equalShare));
        }

        return splits;
    }
    
}
