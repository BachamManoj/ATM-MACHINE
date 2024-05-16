package atm_machine;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Withdraw {
	public static void withdraw(Scanner scanner, String userinlogged) {
        String userID = userinlogged;
        
        double currentBalance = ATM.getUserAccountBalance(userID);

        System.out.println("Enter amount to withdraw:");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (currentBalance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        double updatedBalance = currentBalance - amount;
        ATM.updateUserAccountBalance(userID, updatedBalance);

        ATM.logTransaction(userID, "Withdrawal: -₹" + amount + " " + LocalDateTime.now());

        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current balance: ₹" + updatedBalance);
    }
}
