public class CheckingAccount extends Account{
    private double overDraft;

    public CheckingAccount(String name, String accountNum, double balance, double overDraft) {
        super(name, accountNum, balance);
        this.overDraft = overDraft;
    }

    @Override
     public boolean withdraw(double amount){
        if(getBalance()+overDraft>=amount){
            setBalance(getBalance()-amount);
            addTransaction("Withdrawn money"+amount+ " Current bal:"+getBalance());
            return true;
        }else{
            addTransaction("Withdrawal failed");
            return false;
        }
    }

}
