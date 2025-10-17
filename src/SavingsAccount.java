class SavingsAccount extends Account implements InterestBearing{
    private final double interestRate = 0.0005;

    public SavingsAccount(String accountNumber, double balance, String branch, Customer customer){
        super(accountNumber, balance, branch, customer);
    }

    @Override
    public void withdraw(double amount){
        System.out.println("Withdrawals from Savings Account not allowed");
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
