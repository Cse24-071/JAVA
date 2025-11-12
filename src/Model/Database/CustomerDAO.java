package Model.Database;

import Model.Customer;
import java.util.List;
import java.sql.Connection;



public interface CustomerDAO {
    void update(Customer customer);
    void delete(String id);
    List<Customer> getAllCustomers();

    // Add these for login and registration using CustomerDatabase
    boolean register(CustomerDatabase customer);
    CustomerDatabase login(String username, String password);

    // ✅ Add this to expose the active DB connection
    Connection getConnection();


}