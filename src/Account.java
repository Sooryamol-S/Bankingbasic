import java.util.ArrayList;

public class Account implements Comparable<Account>{
    private String name;
    private String accountNum;
    private double balance;
    private ArrayList<String> transactions;

    public Account(String name, String accountNum, double balance) {
        this.name = name;
        this.accountNum = accountNum;
        this.balance = balance;
        transactions=new ArrayList<>();
        transactions.add("Account created with balance:"+balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount + " | Balance: " + balance);
            return true;  // ✅ Withdrawal successful
        } else {
            transactions.add("Withdrawal failed: Insufficient balance");
            System.out.println("❌ Insufficient balance!");
            return false;  // ❌ Withdrawal failed
        }
    }

    public void deposit(double amount){
        balance+=amount;
        transactions.add("Deposited:"+amount+" current balance"+balance);
    }
    public ArrayList<String> transactionHistory(){
        return transactions;
    }

    public String getAccountInfo() {
        // Return a string containing the name, account number, and balance
        return "Name: " + name + ", Account Number: " + accountNum + ", Balance: " + balance;
    }
    protected void addTransaction(String message) {
        transactions.add(message);
    }
    @Override
    public int compareTo(Account account){
        return Double.compare(this.balance,account.getBalance());
    }

}
