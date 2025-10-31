package Model;

public class SavingsAccount extends Account implements InterestBearing {
     final double interestRate = 0.0005;

    public SavingsAccount(String id,String accountNumber, double balance, String branch, Customer customer){
        super(id,accountNumber, balance, branch, customer,"SAVINGS");
    }

    @Override
    public boolean withdraw(double amount){
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;

    }

    @Override
    public double calculateInterest(){
        return balance * interestRate;
    }


    @Override
    public void applyInterest() {
        balance += calculateInterest();
    }
}
