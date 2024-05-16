package atm_machine;

import java.util.ArrayList;

public class View_balance {
	public static void viewBalance(ArrayList<User> users, String userinlogged) {
        String userID = userinlogged;

        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                System.out.println("Current balance for user " + userID + ": ₹" + user.getBalance());
                return;
            }
        }
        System.out.println("User not found.");
    }
}
