package Model.Database;

import Model.Account;
import Model.ChequeAccount;
import Model.Customer;
import Model.InvestmentAccount;
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
        String sql = "INSERT INTO accounts (id, customer_id, account_type, account_number, balance, branch) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getId());
            stmt.setString(2, account.getCustomerId());
            stmt.setString(3, account.getAccountType());
            stmt.setString(4, account.getAccountNumber());
            stmt.setDouble(5, account.getBalance());
            stmt.setString(6, account.getBranch());
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
                return mapResultSetToAccount(rs);
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
                Account account = mapResultSetToAccount(rs);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        String type = rs.getString("account_type");
        Customer stubCustomer = new Customer(rs.getString("customer_id"), "", "", "", "", "", "", "");
        String id = rs.getString("id");
        String number = rs.getString("account_number");
        double balance = rs.getDouble("balance");
        String branch = rs.getString("branch");

        return switch (type.toLowerCase()) {
            case "savings" -> new SavingsAccount(id, number, balance, branch, stubCustomer);
            case "cheque" -> new ChequeAccount(id, number, balance, branch, stubCustomer, "Unknown Employer", "Unknown Address");
            case "investment" -> new InvestmentAccount(id, number, balance, branch, stubCustomer);
            default -> null;
        };
    }
}