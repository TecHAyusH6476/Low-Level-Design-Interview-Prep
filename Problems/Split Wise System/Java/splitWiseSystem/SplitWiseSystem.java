package splitWiseSystem;

import java.util.HashMap;
import java.util.List;

import group.Group;
import user.User;
import expense.Expense;

public class SplitWiseSystem {
    private static volatile SplitWiseSystem instance;
    private HashMap<Integer, User> users;
    private HashMap<Integer, Group> groups;

    private SplitWiseSystem() {
        users = new HashMap<>();
        groups = new HashMap<>();
    }

    public static SplitWiseSystem getInstance() {
        if (instance == null) {
            synchronized (SplitWiseSystem.class) {
                if (instance == null) {
                    instance = new SplitWiseSystem();
                }
            }
        }
        return instance;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public HashMap<Integer, Group> getGroups() {
        return groups;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void createGroup(int groupId, User admin, List<User> members) {
        Group group = new Group(groupId, admin, members);
        groups.put(group.getId(), group);
    }

    public void addExpenseToGroup(int groupId, int[] userIds, Expense expense, Double[] splitValues) {
        Group group = groups.get(groupId);
        if (group != null) {
            // get users from userIds
            List<User> users = group.getMembers().stream()
                    .filter(user -> {
                        for (int id : userIds) {
                            if (user.getId() == id) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .toList();

            expense.setSplits(users, splitValues);

            // update balances for each user
            for (var split : expense.getSplits()) {
                User user = split.getUser();
                double amount = split.getAmount();
                if (user.getId() != expense.getPaidBy().getId()) {
                    expense.getPaidBy().getBalanceSheet().updateBalance(user, +amount);
                    user.getBalanceSheet().updateBalance(expense.getPaidBy(), -amount);
                }
            }
            group.addExpense(expense);
        }
    }
    
    public synchronized void settleUp(int payerId, int payeeId, double amount) throws IllegalArgumentException {
        User payer = users.get(payerId);
        User payee = users.get(payeeId);
        if (payer != null && payee != null) {
            // check if payer lends money to payee
            double currentBalance = payer.getBalanceSheet().getBalanceWithUser(payee);
            if (currentBalance > 0) {
                throw new IllegalArgumentException("Payer does not owe to payee.");
            }

            if (-currentBalance < amount) {
                throw new IllegalArgumentException("Payer is trying to settle more than owed amount.");
            }

            payer.getBalanceSheet().updateBalance(payee, +amount);
            payee.getBalanceSheet().updateBalance(payer, -amount);

            System.out.println("Settlement of $" + amount + " from " + payer.getName() + " to " + payee.getName() + " completed.");
        }
    }
}
