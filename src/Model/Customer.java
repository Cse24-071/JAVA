package Model;

public class Customer {
    String Id;
    String firstName;
    String lastName;
    String email;
    String address;
    String phoneNumber;

    Account[] accounts;
    int accountCount;

    // Constructor with ID (used when reading from database)
    public Customer(String Id, String firstName, String lastName, String email, String address, String phoneNumber){
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = new Account[3];
        this.accountCount = 0;
    }
// Constructor without ID (used when creating new customers)
    public Customer(String firstName, String lastName, String email, String address, String phoneNumber){
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

    // Getters and setters for JDBC
    public String getId() { return Id; }
    public void setId(String id) { this.Id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

}
