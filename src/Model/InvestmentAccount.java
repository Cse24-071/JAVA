package Model;

public class InvestmentAccount extends Account implements InterestBearing {
     final double interestRate = 0.0005;
     final double initialDeposit = 500.00;


    public InvestmentAccount(String id, String accountNumber, double balance, String branch, Customer customer){
        super(id, accountNumber, balance, branch, customer,"INVESTMENT");
        if (balance < initialDeposit){
            System.out.println("Deposit must be atleast BWP500.00");
        }
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