package Model;

import java.util.IdentityHashMap;
import java.util.UUID;

public class Customer {
    String Id;
    String firstName;
    String lastName;
    String email;
    String address;
    String phoneNumber;
    String username;
    String password;

    Account[] accounts;
    int accountCount;

// Constructor without ID (used when creating new customers)
    public Customer(String Id, String firstName, String lastName, String email, String address, String phoneNumber, String username, String password) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.accounts = new Account[3];
        this.accountCount = 0;
    }

    // No-argument constructor (required for JavaFX and DAO instantiation)
    public Customer() {
        this.accounts = new Account[3];
        this.accountCount = 0;
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

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
