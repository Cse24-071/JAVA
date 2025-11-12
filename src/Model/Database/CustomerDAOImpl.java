package Model.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Customer;

public class CustomerDAOImpl implements CustomerDAO {
    private final Connection connection;

    public CustomerDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }



    public boolean register(CustomerDatabase customer) {
        String sql = "INSERT INTO customers (first_name, last_name, username, password, email, address, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getUsername());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getEmail());
            stmt.setString(6, customer.getAddress());
            stmt.setString(7, customer.getPhoneNumber());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, address = ?, phone_number = ?, username = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setString(6, customer.getUsername());
            stmt.setString(7, customer.getPassword());
            stmt.setString(8, customer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    public CustomerDatabase login(String username, String password) {
        String sql = "SELECT * FROM customers WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    return new CustomerDatabase(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("phone_number"),
                            rs.getString("username"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

