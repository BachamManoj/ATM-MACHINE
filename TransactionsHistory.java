package atm_machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TransactionsHistory {
	private static final File TRANSACTION_HISTORY_FILE = new File("D:\\java data\\OCTANET-MANOJ\\src\\atm_machine\\transactionHistory.txt");
    
	public static void viewTransactionHistory(String user_id) {
        System.out.println("Transaction History:");
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] Transaction_data = line.split(":");
                   String userid=Transaction_data[0];
                   if(userid.equals(user_id))
                   {
                	   System.out.println(line);
                   }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}