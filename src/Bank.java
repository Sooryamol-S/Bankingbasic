import java.util.ArrayList;
import java.util.Collections;

public class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void withdrawMoney(Account account, double amount) {
        account.withdraw(amount);
    }

    public void depositMoney(Account account, double amount) {
        account.deposit(amount);
    }

    public ArrayList<Account> getList() {
        return accounts;
    }
    public void showTransactionHistory(Account account) {
        System.out.println("🔹 Transaction History for " + account.getName() + ":");
        for (String transaction : account.transactionHistory()) {
            System.out.println(transaction);
        }
    }
    public boolean transferMoney(String senderAccNum, String receiverAccNum, double amount) {
        Account s=null;
        Account r=null;
        for(Account a:accounts){
            if(a.getAccountNum().equals(senderAccNum)){
                   s=a;
            }
            if(a.getAccountNum().equals(receiverAccNum)){
                r=a;
            }
        }
        if (s == null) {
            System.out.println("❌ Sender account not found!");
            return false;
        }
        if (r == null) {
            System.out.println("❌ Receiver account not found!");
            return false;
        }
        if (s.getBalance() < amount) {
            System.out.println("❌ Insufficient balance in sender's account!");
            return false;
        }

        if(s.getBalance()>=amount){
            s.withdraw(amount);
           r.deposit(amount);
        }
        s.transactionHistory().add("Transferred ₹" + amount + " to " + r.getAccountNum());
        r.transactionHistory().add("Received ₹" + amount + " from " + s.getAccountNum());


        System.out.println("✅ Transfer successful: ₹" + amount + " from " + senderAccNum + " to " + receiverAccNum);
        return true;

    }
    public void displayTop(int n) {
        if (accounts.isEmpty()) {
            System.out.println("⚠️ No accounts available!");
            return;
        }

        Collections.sort(accounts, Collections.reverseOrder()); // Sort in descending order

        System.out.println("\n🏆 Top " + n + " Accounts by Balance:");
        for (int i = 0; i < Math.min(n, accounts.size()); i++) {
            System.out.println(accounts.get(i).getAccountInfo());
        }
    }

}
