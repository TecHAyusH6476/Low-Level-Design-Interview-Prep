package expense;

import java.util.UUID;

import user.User;

public class Split {
    private String id;
    private User user;
    private double amount;

    public Split(User user, double amount) {
        id = UUID.randomUUID().toString();
        this.user = user;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }
}
