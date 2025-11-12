package View;

import Model.*;
import Model.Database.AccountDAO;
import Model.Database.AccountDAOImpl;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountController {

    @FXML private TableView<TransactionRecord> transactionTable;

    @FXML private Label ChequeBalanceLabel;
    @FXML
    private TableColumn<TransactionRecord, Double> amountCol;
    @FXML
    private TableColumn<TransactionRecord, Double> balanceCol;
    @FXML private TableColumn<TransactionRecord, String> accountTypeCol;
    @FXML private Button chequeDepositButton;
    @FXML private Tab chequeTab;
    @FXML private Button chequeWithdrawButton;
    @FXML private TableColumn<TransactionRecord, String> dateCol;
    @FXML private Label investmentBalanceLabel;
    @FXML private Button investmentDepositButton;
    @FXML private Tab investmentTab;
    @FXML private Button investmentWithdrawButton;
    @FXML private MenuButton menuButton;
    @FXML private Label savingsBalanceLabel;
    @FXML private Button savingsDepositButton;
    @FXML private Tab savingsTab;
    @FXML private Label totalInvestedLabel;
    @FXML private TableColumn<TransactionRecord, String> transactionTypeCol;
    @FXML private MenuItem transcactionHistoryMenuItem;
    @FXML private Button transferButton;
    @FXML private ChoiceBox<String> transferDestinationChoiceBox;
    @FXML private Label customerField;

    private ObservableList<TransactionRecord> transactionHistory = FXCollections.observableArrayList();


    private Customer customer;
    private Connection connection;
    private AccountDAO accountDAO;

    private ChequeAccount chequeAccount;
    private SavingsAccount savingsAccount;
    private InvestmentAccount investmentAccount;

    @FXML
    public void initialize() {
        transferDestinationChoiceBox.getItems().addAll("Savings", "Investment");

        transactionTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        accountTypeCol.setCellValueFactory(new PropertyValueFactory<>("account"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));

        transactionTable.setItems(transactionHistory);
    }

    public void setCustomer(Customer customer, Connection connection) {
        this.customer = customer;
        this.connection = connection;
        this.accountDAO = new AccountDAOImpl(connection);


        customerField.setText(customer.getFirstName() + " " + customer.getLastName());

        // Initialize accounts after customer is set
        this.chequeAccount = new ChequeAccount( UUID.randomUUID().toString(), "123456", 1500.0, "Main Branch", customer,
                "TechCorp Ltd", "Gaborone CBD"
        );

        this.savingsAccount = new SavingsAccount( UUID.randomUUID().toString(), "SAV001", 2000.0, "Main Branch", customer
        );

        this.investmentAccount = new InvestmentAccount( UUID.randomUUID().toString(), "INV001", 5000.0, "Main Branch", customer
        );

        accountDAO.create(chequeAccount);
        accountDAO.create(savingsAccount);
        accountDAO.create(investmentAccount);


        updateBalances();
    }

    private void updateBalances() {
        ChequeBalanceLabel.setText("Balance: P" + chequeAccount.getBalance());
        savingsBalanceLabel.setText("Balance: P" + savingsAccount.getBalance());
        investmentBalanceLabel.setText("Balance: P" + investmentAccount.getBalance());

        double total = chequeAccount.getBalance() + savingsAccount.getBalance() + investmentAccount.getBalance();
        totalInvestedLabel.setText("Total: P" + total);
    }

        public void logTransaction(String type, String account, double amount, double balance) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            transactionHistory.add(new TransactionRecord(type, timestamp, account, amount, balance));

        }

    private double getAmountFromUser(String action) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Amount");
        dialog.setHeaderText("Enter amount to " + action);
        dialog.setContentText("Amount (P):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                return Double.parseDouble(result.get());
            } catch (NumberFormatException e) {
                showAlert("Invalid number entered!");
            }
        }
        return 0.0;
    }

    @FXML
    void depositCheque(ActionEvent event) {
        double amount = getAmountFromUser("deposit");
        chequeAccount.deposit(amount);
        logTransaction("Deposit", "Cheque", amount, chequeAccount.getBalance());
        updateBalances();
    }

    @FXML
    void depositInvestment(ActionEvent event) {
        double amount = getAmountFromUser("deposit");
        investmentAccount.deposit(amount);
        logTransaction("Deposit", "Cheque", amount, chequeAccount.getBalance());
        updateBalances();
    }

    @FXML
    void depositSavings(ActionEvent event) {
        double amount = getAmountFromUser("deposit");
        savingsAccount.deposit(amount);
        logTransaction("Deposit", "Cheque", amount, chequeAccount.getBalance());
        updateBalances();
    }

    @FXML
    void handleTransfer(ActionEvent event) {
        double amount = getAmountFromUser("transfer");
        String destination = transferDestinationChoiceBox.getValue();

        if (!chequeAccount.withdraw(amount)) {
            showAlert("Not enough money in Cheque Account.");
            return;
        }

        switch (destination) {
            case "Savings" -> savingsAccount.deposit(amount);
            case "Investment" -> investmentAccount.deposit(amount);
            default -> {
                showAlert("Please choose where to transfer.");
                chequeAccount.deposit(amount); // refund
                return;
            }
        }

        updateBalances();
    }

    @FXML
    void withdrawCheque(ActionEvent event) {
        double amount = getAmountFromUser("withdraw");
        if (chequeAccount.withdraw(amount)) {
            logTransaction("Withdraw", "Cheque", amount, chequeAccount.getBalance());
            updateBalances();
        } else {
            showAlert("Not enough money in Cheque Account.");
        }
    }

    @FXML
    void withdrawInvestment(ActionEvent event) {
        double amount = getAmountFromUser("withdraw");
        if (investmentAccount.withdraw(amount)) {
            logTransaction("Withdraw", "Cheque", amount, chequeAccount.getBalance());
            updateBalances();
        } else {
            showAlert("Not enough money in Investment Account.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}