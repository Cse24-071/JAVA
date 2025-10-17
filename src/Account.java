abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected Customer customer;

    public Account (String accountNumber, double balance, String branch, Customer customer){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.customer = customer;
    }

    public void deposit (double amount){
        balance += amount;
    }

    public abstract void withdraw(double amount);

    public abstract double calculateInterest();


    public double getBalance() {
        return balance;
    }

        public String getAccountNumber () {
            return accountNumber;
        }

        public String getBranch () {
            return branch;
        }

        public Customer getCustomer () {
            return customer;

        }
    }
