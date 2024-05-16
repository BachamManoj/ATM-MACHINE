package atm_machine;

public class User {
    private String userID;
    private String pin;
    private double balance;

    public User(String userID, String pin, double balance) {
        this.userID = userID;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserID() {
        return userID;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
