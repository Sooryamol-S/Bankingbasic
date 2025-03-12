import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n--- BANK MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Account");
            System.out.println("2. Remove Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. View All Accounts");
            System.out.println("7. transaction history");
            System.out.println("6. Exit");
            System.out.println("8. transfer money");
            System.out.println("9. Get top n balance accounts:");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Account
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accountNum = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();
                    System.out.println("Which type of account u need:?\n 1.Savings\n 2.checking:" );
                    int s=sc.nextInt();
                    sc.nextLine();
                    Account newAccount;
                    if(s==1){
                        newAccount = new SavingsAccount(name, accountNum, balance);
                    }
                    else if(s==2) {
                        System.out.println("enter overdraft limit");
                        double o=sc.nextDouble();
                        sc.nextLine();
                        newAccount=new CheckingAccount(name,accountNum,balance,o);
                    }
                    else {
                    System.out.println("❌ Invalid choice! Account not created.");
                    break;
                    }
                    bank.addAccount(newAccount);
                    System.out.println("✅ Account added successfully!");
                    break;

                case 2: // Remove Account
                    System.out.print("Enter Account Number to remove: ");
                    String removeAccNum = sc.next();
                    Account accToRemove = null;
                    for (Account acc : bank.getList()) {
                        if (acc.getAccountNum().equals(removeAccNum)) {
                            accToRemove = acc;
                            break;
                        }
                    }
                    if (accToRemove != null) {
                        bank.removeAccount(accToRemove);
                        System.out.println("✅ Account removed successfully!");
                    } else {
                        System.out.println("❌ Account not found!");
                    }
                    break;

                case 3: // Deposit Money
                    System.out.print("Enter Account Number: ");
                    String depAccNum = sc.next();
                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount = sc.nextDouble();
                    boolean foundDep = false;
                    for (Account acc : bank.getList()) {
                        if (acc.getAccountNum().equals(depAccNum)) {
                            bank.depositMoney(acc, depositAmount);
                            System.out.println("✅ Amount Deposited Successfully!");
                            foundDep = true;
                            break;
                        }
                    }
                    if (!foundDep) {
                        System.out.println("❌ Account not found!");
                    }
                    break;

                case 4: // Withdraw Money
                    System.out.print("Enter Account Number: ");
                    String withAccNum = sc.next();
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    boolean foundWith = false;

                    for (Account acc : bank.getList()) {  // Assuming bank.getList() returns all accounts
                        if (acc.getAccountNum().equals(withAccNum)) {
                            boolean success = acc.withdraw(withdrawAmount);  // Call the method directly

                            if (success) {
                                System.out.println("✅ Amount Withdrawn Successfully!");
                            }
                            // No need to check balance manually here since withdraw() already prints failure message
                            foundWith = true;
                            break;
                        }
                    }

                    if (!foundWith) {
                        System.out.println("❌ Account not found!");
                    }
                    break;


                case 5: // View All Accounts
                    System.out.println("\n--- Account List ---");
                    if (bank.getList().isEmpty()) {
                        System.out.println("No accounts available.");
                    } else {
                        for (Account acc : bank.getList()) {
                            System.out.println(acc.getAccountInfo());
                        }
                    }
                    break;

                case 6: // Exit
                    System.out.println("✅ Exiting... Thank you!");
                    sc.close();
                    return;

                case 7:
                    System.out.println("Enter the acc numb:");
                    String ac=sc.nextLine();
                    Account acco=null;
                    for(Account acc: bank.getList()){
                        if(acc.getAccountNum().equals(ac)){
                            acco=acc;
                            break;
                        }
                    }
                    if(acco==null){
                        System.out.println("No account found for given number");
                    }
                    else{
                        ArrayList<String> suu=acco.transactionHistory();
                        for(String a:suu){
                            System.out.println(a);
                        }
                    }
                    break;
                case 8: // Transfer Money
                    System.out.print("Enter Sender Account Number: ");
                    String senderAcc = sc.next();
                    System.out.print("Enter Receiver Account Number: ");
                    String receiverAcc = sc.next();
                    System.out.print("Enter Amount to Transfer: ");
                    double transferAmount = sc.nextDouble();

                    boolean success = bank.transferMoney(senderAcc, receiverAcc, transferAmount);
                    if (!success) {
                        System.out.println("❌ Transfer Failed!");
                    }
                    break;
                case 9:
                    System.out.println("Enter the n:");
                    int n=sc.nextInt();
                    sc.nextLine();
                    bank.displayTop(n);
                    break;


                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
}
