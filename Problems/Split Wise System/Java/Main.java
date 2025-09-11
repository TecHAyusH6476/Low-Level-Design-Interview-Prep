import java.util.List;

import expense.Expense;
import splitWiseSystem.SplitWiseSystem;
import user.User;

public class Main {
    public static void main(String[] args) {
        SplitWiseSystem splitWiseSystem = SplitWiseSystem.getInstance();

        // Add users
        splitWiseSystem.addUser(new User(1, "Alice", "1234567890"));
        splitWiseSystem.addUser(new User(2, "Bob", "0987654321"));
        splitWiseSystem.addUser(new User(3, "Charlie", "1122334455"));
        splitWiseSystem.addUser(new User(4, "David", "5566778899"));

        // Create a group
        splitWiseSystem.createGroup(1, splitWiseSystem.getUsers().get(1), List.of(
                splitWiseSystem.getUsers().get(1),
                splitWiseSystem.getUsers().get(2),
                splitWiseSystem.getUsers().get(3),
                splitWiseSystem.getUsers().get(4)
        ));

        // Add an expense to the group
        Expense expense = new Expense(1, "Dinner", 4000, splitWiseSystem.getUsers().get(1),
                new expense.strategy.SplitStrategy(new expense.strategy.ExactSplit()));

        splitWiseSystem.addExpenseToGroup(1, new int[]{1, 2, 3}, expense, new Double[]{2000.0, 500.0, 1500.0});

        // Print balances
        for (User user : splitWiseSystem.getUsers().values()) {
            user.getBalanceSheet().printBalanceSheet();
        }

        // settle up balances
        try{
            splitWiseSystem.settleUp(2, 1, 250);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

        // Print balances after settlement
        System.out.println("After settlement:");
        for (User user : splitWiseSystem.getUsers().values()) {
            user.getBalanceSheet().printBalanceSheet();
        }
    }
}
