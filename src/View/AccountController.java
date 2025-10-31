package View;
import Model.Customer;
import Model.ChequeAccount;
import Model.SavingsAccount;
import Model.InvestmentAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;



public class AccountController {
    @FXML
    private Label ChequeBalanceLabel;

    @FXML
    private TableColumn<?, ?> accountTypeCol;

    @FXML
    private Button chequeDepositButton;

    @FXML
    private Tab chequeTab;

    @FXML
    private Button chequeWithdrawButton;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private Label investmentBalanceLabel;

    @FXML
    private Button investmentDepositButton;

    @FXML
    private Tab investmentTab;

    @FXML
    private Button investmentWithdrawButton;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Label savingsBalanceLabel;

    @FXML
    private Button savingsDepositButton;

    @FXML
    private Tab savingsTab;

    @FXML
    private Label totalInvestedLabel;

    @FXML
    private TableColumn<?, ?> transactionTypeCol;

    @FXML
    private MenuItem transcactionHistoryMenuItem;

    @FXML
    private Button transferButton;

    @FXML
    private ChoiceBox<String> transferDestinationChoiceBox;

    // --- Core model objects ---
    final Customer customer = new Customer("1","Atang", "Molefi", "atang@example.com", "Tlokweng", "71234567");

    final ChequeAccount chequeAccount = new ChequeAccount(
            "CHQ001",         // id
            "123456",         // accountNumber
            1500.0,           // balance
            "Main Branch",    // branch
            customer,         // Customer object
            "TechCorp Ltd",   // employerName
            "Gaborone CBD"    // employerAddress
    );


    final SavingsAccount savingsAccount = new SavingsAccount(
            "1",              // id (e.g., database ID or internal identifier)
            "SAV001",         // accountNumber
            2000.0,           // balance
            "Main Branch",    // branch
            customer          // Customer object

    );

    final InvestmentAccount investmentAccount = new InvestmentAccount(
            "INV001-ID", "INV001", 5000.0, "Main Branch", customer);



    @FXML
    public void initialize() {
        transferDestinationChoiceBox.getItems().addAll("Savings", "Investment");
        updateBalances();
    }

    // --- Update all balance labels ---
    private void updateBalances() {
        ChequeBalanceLabel.setText("Balance: P" + chequeAccount.getBalance());
        savingsBalanceLabel.setText("Balance: P" + savingsAccount.getBalance());
        investmentBalanceLabel.setText("Balance: P" + investmentAccount.getBalance());

        double total = chequeAccount.getBalance() + savingsAccount.getBalance() + investmentAccount.getBalance();
        totalInvestedLabel.setText("Total: P" + total);

    }

    // --- Helper: Ask user for an amount ---
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
        updateBalances();



    }

    @FXML
    void depositInvestment(ActionEvent event) {
        double amount = getAmountFromUser("deposit");
        investmentAccount.deposit(amount);
        updateBalances();



    }

    @FXML
    void depositSavings(ActionEvent event) {
        double amount = getAmountFromUser("deposit");
        savingsAccount.deposit(amount);
        updateBalances();



    }

    @FXML
    void handleTransactionHistory(ActionEvent event) {

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
            updateBalances();
        } else {
            showAlert("Not enough money in Cheque Account.");
        }

    }

    @FXML
    void withdrawInvestment(ActionEvent event) {
        double amount = getAmountFromUser("withdraw");
        if (investmentAccount.withdraw(amount)) {
            updateBalances();
        } else {
            showAlert("Not enough money in Investment Account.");
        }

    }

    // --- Helper method to show alerts ---
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
