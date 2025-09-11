package user;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    private String id;
    private User user;
    private Map<User, Double> balances;

    public BalanceSheet(User user) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.balances = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Map<User, Double> getBalances() {
        return balances;
    }

    public void updateBalance(User user, double amount) {
        balances.put(user, balances.getOrDefault(user, 0.0) + amount);
    }

    public double getBalanceWithUser(User user) {
        return balances.getOrDefault(user, 0.0);
    }

    public void printBalanceSheet() {
        System.out.println("Balance Sheet for " + user.getName() + ":");
        System.out.println("---------------------------");
        double totalMoneyOwed = 0.0;
        double totalMoneyLent = 0.0;

        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            User otherUser = entry.getKey();
            double amount = entry.getValue();
            if (amount > 0) {
                System.out.println(otherUser.getName() + " owes you: $" + amount);
                totalMoneyLent += amount;
            } else if (amount < 0) {
                System.out.println("You owe " + otherUser.getName() + ": $" + (-amount));
                totalMoneyOwed += (-amount);
            } else {
                System.out.println("No balance with " + otherUser.getName());
            }
        }

        System.out.println("Total money lent: $" + totalMoneyLent);
        System.out.println("Total money owed: $" + totalMoneyOwed);
        System.out.println();
    }
    
}
