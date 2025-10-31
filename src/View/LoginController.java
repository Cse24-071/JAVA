package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


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

        if (username.equals("admin") && password.equals("1234")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountView.fxml"));
                Parent accountRoot = loader.load();

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
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Please enter a username and password to register.");
            return;
        }

        // Simulate registration success
        showAlert("Registration successful for user: " + username + ".");
        // TODO: Save user to database or switch to login screen


    }

    private void showAlert(String message) {
        messageLabel.setText(message);
    }

}
