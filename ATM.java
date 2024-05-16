package atm_machine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private static final File USER_DATA_FILE = new File("D:\\java data\\OCTANET-MANOJ\\src\\atm_machine\\userData.txt");
    private static final File TRANSACTION_HISTORY_FILE = new File("D:\\java data\\OCTANET-MANOJ\\src\\atm_machine\\transactionHistory.txt");
    
    static String userinlogged=null;
    private static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        
    	
        loadUserData();
        Scanner scanner = new Scanner(System.in);

        if (login(scanner)) {
            System.out.println("Login successful. Welcome to the ATM!");
            boolean quit = false;
            while (!quit) {
                System.out.println("\nChoose an option:");
                System.out.println("1. View Transaction History");
                System.out.println("2. View Balance");
                System.out.println("3. Withdraw");
                System.out.println("4. Deposit");
                System.out.println("5. Transfer");
                System.out.println("6. Quit");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                    	TransactionsHistory.viewTransactionHistory(userinlogged);
                        break;
                    case 2:
                        View_balance.viewBalance(users,userinlogged);
                        break;
                    case 3:
                        Withdraw.withdraw(scanner,userinlogged);
                        break;
                    case 4:
                        Deposit.deposit(scanner,userinlogged);
                        break;
                    case 5:
                        Transfer.transfer(scanner,userinlogged);
                        break;
                    case 6:
                        System.out.println("Exiting.......");
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            scanner.close();
        } else {
            System.out.println("Invalid user ID or PIN. TRY AGAIN");
        }

        
        saveUserData();
    }

    private static boolean login(Scanner scanner) {
        System.out.println("Enter user ID:");
        String userID = scanner.next();
        System.out.println("Enter PIN:");
        String pin = scanner.next();

        for (User user : users) {
            if (user.getUserID().equals(userID) && user.getPin().equals(pin)) {
            	userinlogged=userID;
                return true;
            }
        }
        return false;
    }

    private static void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String userID = userData[0];
                    String pin = userData[1];
                    double balance = Double.parseDouble(userData[2]);
                    users.add(new User(userID, pin, balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (User user : users) {
                writer.write(user.getUserID() + "," + user.getPin() + "," + user.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean recipientExists(String recipientID) {
        for (User user : users) {
            if (user.getUserID().equals(recipientID)) {
                return true;
            }
        }
        return false;
    }

    public static double getUserAccountBalance(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user.getBalance();
            }
        }
        return 0; 
    }

    public static void updateUserAccountBalance(String userID, double balance) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                user.setBalance(balance);
                return;
            }
        }
    }

    public static void logTransaction(String userID, String transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_HISTORY_FILE, true))) {
            writer.write(userID + ": " + transaction);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
