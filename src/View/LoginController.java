package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;


import Model.Database.CustomerDatabase;
import Model.Database.CustomerDAO;
import Model.Database.CustomerDAOImpl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.Alert;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Button forgotPasswordButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    private CustomerDAO customerDAO;

    @FXML
    public void initialize() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            customerDAO = new CustomerDAOImpl(connection);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection failed", e);
            showAlert("Unable to connect to the database.");
        }
    }




    @FXML
    void handleForgotPassword(ActionEvent event) {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            showAlert("Please enter your username to recover your password.");
        } else {
            showAlert("Password recovery link sent to the email associated with " + username + ".");
        }


    }

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Please enter both username and password.");
            return;
        }

        CustomerDatabase customer = customerDAO.login(username, password);
        if (customer != null) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountView.fxml"));
                Parent accountRoot = loader.load();

                // ✅ Get controller and pass customer + connection
                AccountController accountController = loader.getController();
                accountController.setCustomer(customer.toCustomer(), customerDAO.getConnection());


                // Get current stage from the login button
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene accountScene = new Scene(accountRoot);
                stage.setScene(accountScene);
                stage.setTitle("Account Dashboard");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Failed to load account view.");
            }
        } else {
            showAlert("Invalid username or password.");
        }


}

    @FXML
    void handleRegister(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Registration.fxml"));
            Parent registrationRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registrationRoot));
            stage.setTitle("Customer Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Replace with proper logging if needed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to load registration screen");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


}

    private void showAlert(String message) {
        messageLabel.setText(message);
    }

}
