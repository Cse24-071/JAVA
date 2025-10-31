package Model.Database;

import Model.Customer;
import java.util.List;

public interface CustomerDAO {
    void create(Customer customer);
    Customer read(String id);
    void update(Customer customer);
    void delete(String id);
    List<Customer> getAllCustomers();
}
