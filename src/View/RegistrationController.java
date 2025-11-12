package View;

import Model.Database.DatabaseUtil;
import Model.Database.CustomerDAOImpl;
import Model.Database.CustomerDAO;
import Model.Database.CustomerDatabase;


import java.sql.SQLException;
import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;


public class RegistrationController {

    @FXML
    private TextField LastNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void handleRegister(ActionEvent event) {

        // Create CustomerDatabase object from form fields
        CustomerDatabase customer = new CustomerDatabase(
                firstNameField.getText(),
                LastNameField.getText(),
                emailField.getText(),
                addressField.getText(),
                phoneNumberField.getText(),
                usernameField.getText(),
                passwordField.getText()
        );

        try {
            Connection conn = DatabaseUtil.getConnection();
            CustomerDAO customerDAO = new CustomerDAOImpl(conn);
            boolean success = customerDAO.register(customer); // make sure register() returns boolean

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("Welcome to our bank! After registration, Cheque, Investment, and Savings accounts are automatically opened for you. Thank you.");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountView.fxml"));
                Parent accountRoot = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(accountRoot));
                stage.show();

            } else {
                showError("Registration failed. Username might already exist.");
            }

        } catch (SQLException e) {
                e.printStackTrace();
                showError("Database error: " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                showError("UI loading error: " + e.getMessage());
            }
        }

        private void showError(String message) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Something went wrong");
            errorAlert.setContentText(message);
            errorAlert.showAndWait();
        }

    }


