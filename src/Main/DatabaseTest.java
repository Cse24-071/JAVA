package Main;
import Model.Database.CustomerDAOImpl;
import Model.Database.DBConnection;
import Model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class DatabaseTest {
        public static void main(String[] args) {
            try (Connection connection = DBConnection.getConnection()) {
                CustomerDAOImpl dao = new CustomerDAOImpl(connection);

                // Create
                Customer newCustomer = new Customer("Atang", "Molefi", "atang@example.com", "Tlokweng", "71234567");
                dao.create(newCustomer);

                // Read
                Customer retrieved = dao.read("1");
                if (retrieved != null) {
                    System.out.println("Retrieved: " + retrieved.getFullName());
                }

                // Update
                if (retrieved != null) {
                    retrieved.setEmail("atang.updated@example.com");
                    dao.update(retrieved);
                }

                // List All
                List<Customer> all = dao.getAllCustomers();
                for (Customer c : all) {
                    System.out.println("- " + c.getFullName() + " (" + c.getEmail() + ")");
                }

                // Delete
                dao.delete("1");
                System.out.println("Customer deleted.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

