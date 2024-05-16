package atm_machine;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Deposit {
	public static void deposit(Scanner scanner, String userinlogged) {
	     
        String userID = userinlogged;

        System.out.println("Enter amount to deposit:");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        double currentBalance = ATM.getUserAccountBalance(userID);
        double updatedBalance = currentBalance + amount;
        ATM.updateUserAccountBalance(userID, updatedBalance);

        ATM.logTransaction(userID, "Deposit: +₹" + amount + " " + LocalDateTime.now());

        System.out.println("₹" + amount + " deposited successfully.");
        System.out.println("Current balance: ₹" + updatedBalance);
    }
}
