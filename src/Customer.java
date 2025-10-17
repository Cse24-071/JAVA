class Customer {
    String firstName;
    String lastName;
    String email;
    String address;
    String phoneNumber;

    Account[] accounts;
    int accountCount;

    public Customer(String firstName, String lastName, String email,String address, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = new Account[3];
        this.accountCount = 0;
    }

    public void addAccount(Account account){
        if (accountCount < accounts.length){
            accounts[accountCount++] = account;
        }
    }

    public Account[] getAccounts(){
        return accounts;
    }

    public String getFullName(){
        return firstName+""+lastName;
    }
}
