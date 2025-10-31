package Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:bank.db"; // Creates bank.db file in your project folder

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String createCustomers = """
                CREATE TABLE IF NOT EXISTS customers (
                    id TEXT PRIMARY KEY,
                    first_name TEXT,
                    last_name TEXT,
                    email TEXT,
                    address TEXT,
                    phone TEXT
                );
            """;

            String createAccounts = """
                CREATE TABLE IF NOT EXISTS accounts (
                    id TEXT PRIMARY KEY,
                    account_number TEXT,
                    customer_id TEXT,
                    account_type TEXT,
                    balance REAL,
                    branch TEXT,
                    employer_name TEXT,
                    employer_address TEXT,
                    FOREIGN KEY (customer_id) REFERENCES customers(id)
                );
            """;

            String createTransactions = """
                CREATE TABLE IF NOT EXISTS transactions (
                    id TEXT PRIMARY KEY,
                    account_id TEXT,
                    type TEXT,
                    amount REAL,
                    date TEXT,
                    description TEXT,
                    FOREIGN KEY (account_id) REFERENCES accounts(id)
                );
            """;

            stmt.execute(createCustomers);
            stmt.execute(createAccounts);
            stmt.execute(createTransactions);

            System.out.println("Database schema created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}