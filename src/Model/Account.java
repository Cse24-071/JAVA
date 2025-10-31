package Model;

public abstract class Account {
    private String id;
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected Customer customer;
    protected String accountType;

    public Account (String id,String accountNumber, double balance, String branch, Customer customer, String accountType) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.customer = customer;
        this.accountType = accountType;
    }

    public void deposit (double amount){
        balance += amount;
    }

    public abstract boolean withdraw(double amount);

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

    public String getCustomerId() {
        return customer.getId();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    }
