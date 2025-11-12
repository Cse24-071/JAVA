package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TransactionRecord {
    private final SimpleStringProperty type;
    private final SimpleStringProperty date;
    private final SimpleStringProperty account;
    private final SimpleDoubleProperty amount;
    private final SimpleDoubleProperty balance;

    public TransactionRecord(String type, String date, String account, double amount, double balance) {
        this.type = new SimpleStringProperty(type);
        this.date = new SimpleStringProperty(date);
        this.account = new SimpleStringProperty(account);
        this.amount = new SimpleDoubleProperty(amount);
        this.balance = new SimpleDoubleProperty(balance);
    }

    public String getType() { return type.get(); }
    public String getDate() { return date.get(); }
    public String getAccount() { return account.get(); }
    public double getAmount() { return amount.get(); }
    public double getBalance() { return balance.get(); }
}


