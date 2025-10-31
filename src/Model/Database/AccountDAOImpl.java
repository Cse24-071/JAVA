package Model.Database;

import Model.Account;
import Model.Customer;
import Model.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private final Connection connection;

    public AccountDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Account account) {
        String sql = "INSERT INTO accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getCustomerId());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logger if needed
        }
    }

    @Override
    public Account read(String id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer stubCustomer = new Customer(
                        rs.getString("customer_id"), "", "", "", "", ""
                );
                return new SavingsAccount(
                        rs.getString("id"),               // id
                        rs.getString("account_number"),   // accountNumber
                        rs.getDouble("balance"),          // balance
                        rs.getString("branch"),           // branch
                        stubCustomer                      // customer

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE accounts SET account_type = ?, balance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountType());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> getAccountsByCustomer(String customerId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer stubCustomer = new Customer(
                        rs.getString("customer_id"), "", "", "", "", ""
                );
                accounts.add(new SavingsAccount(
                        rs.getString("id"),               // id
                        rs.getString("account_number"),   // accountNumber
                        rs.getDouble("balance"),          // balance
                        rs.getString("branch"),           // branch
                        stubCustomer                      // customer
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}