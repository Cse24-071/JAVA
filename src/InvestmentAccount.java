class InvestmentAccount extends Account implements InterestBearing {
    private final double interestRate = 0.005;
    private final double initialDeposit = 500.00;


    public InvestmentAccount(String accountNumber, double balance, String branch, Customer customer){
        super(accountNumber, balance, branch, customer);
        if (balance < initialDeposit){
            System.out.println("Deposit must be atleast BWP500.00");
        }
    }

    @Override
    public void withdraw(double amount){
        if (amount <= balance){
            balance -= amount;
        }else {
            System.out.println("insufficient funds");
        }
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