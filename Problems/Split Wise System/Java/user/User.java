package user;

public class User {
    private int id;
    private String name;
    private String contactNo;
    private BalanceSheet balanceSheet;

    public User(int id, String name, String contactNo) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
        this.balanceSheet = new BalanceSheet(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }
}