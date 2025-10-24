class ChequeAccount extends Account{
    private String campanyName;
    private String campanyAddress;

    public ChequeAccount(String accountNumber, double balance, String branch, Customer customer, String employerName, String employerAddress){
        super(accountNumber, balance, branch, customer);
        this.campanyName = employerName;
        this.campanyAddress = employerAddress;
    }

    @Override
    public void withdraw(double amount){
        if (amount <= balance){
            balance -= amount;
        }else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public double calculateInterest(){
        return 0;
    }
}