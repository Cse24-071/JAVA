package Model;

public class ChequeAccount extends Account {
    final String campanyName;
    final String campanyAddress;

    public ChequeAccount(String id,String accountNumber, double balance, String branch, Customer customer, String employerName, String employerAddress){
        super(id,accountNumber, balance, branch, customer,"cheque");
        this.campanyName = employerName;
        this.campanyAddress = employerAddress;
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
        return 0;
    }
}