package group;

import java.util.ArrayList;
import java.util.List;

import expense.Expense;
import user.User;

public class Group {
    private int id;
    private User admin;
    private List<User> members;
    private List<Expense> expenses;

    public Group(int id, User admin, List<User> members) {
        this.id = id;
        this.admin = admin;
        this.members = members;
        this.expenses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public User getAdmin() {
        return admin;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}