package atm_machine;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Transfer {
	public static void transfer(Scanner scanner, String userinlogged) {
        String senderID = userinlogged;

        double senderBalance = ATM.getUserAccountBalance(senderID);

        System.out.println("Enter recipient's user ID:");
        String recipientID = scanner.next();

        System.out.println("Enter amount to transfer:");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (!ATM.recipientExists(recipientID)) {
            System.out.println("Recipient not found.");
            return;
        }

        if (senderBalance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        double senderUpdatedBalance = senderBalance - amount;
        ATM.updateUserAccountBalance(senderID, senderUpdatedBalance);

        double recipientBalance = ATM.getUserAccountBalance(recipientID);
        double recipientUpdatedBalance = recipientBalance + amount;
        ATM.updateUserAccountBalance(recipientID, recipientUpdatedBalance);

        ATM.logTransaction(senderID, "Transfer: -₹" + amount + " to " + recipientID + " " + LocalDateTime.now());
        ATM.logTransaction(recipientID, "Transfer: +₹" + amount + " from " + senderID + " " + LocalDateTime.now());

        System.out.println("₹" + amount + " transferred successfully to " + recipientID);
        System.out.println("Current balance for sender " + senderID + ": ₹" + senderUpdatedBalance);
    }

}
