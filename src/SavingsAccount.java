public class SavingsAccount extends Account {
    public SavingsAccount(String name, String accountNum,double balance){
        super(name,accountNum,balance);
    }
    @Override
    public boolean withdraw(double amount) {
        if (amount <= getBalance()) {
            setBalance(getBalance()-amount);
            addTransaction("Withdrawn: " + amount + " | Remaining Balance: " + getBalance());
            return true;
        } else {
            addTransaction("❌ Withdrawal failed: Insufficient balance!");
            System.out.println("❌ Withdrawal failed: Insufficient balance!");
            return false;
        }
    }

}
