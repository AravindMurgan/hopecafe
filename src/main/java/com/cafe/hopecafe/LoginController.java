package com.cafe.hopecafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event){
        loginMessageLabel.setText("Your trying to login");
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        // Perform validation
        if (username.isEmpty() || password.isEmpty()) {
            loginMessageLabel.setText("Username and password are required.");
        } else if (!isValidUsername(username)) {
            loginMessageLabel.setText("Invalid username format.");
        } else if (!isValidPassword(password)) {
            loginMessageLabel.setText("Invalid password format.");
        } else {
            // Validation passed, proceed with login action
            loginMessageLabel.setText("Attempting to log in...");
            // Perform login action here
        }


    }


    private boolean isValidUsername(String username) {
        // Implement your username validation logic here
        // For example, you might check if the username follows certain rules
        return !username.contains(" ");
    }

    private boolean isValidPassword(String password) {
        // Implement your password validation logic here
        // For example, you might check if the password meets certain criteria
        return password.length() >= 8;
    }
}