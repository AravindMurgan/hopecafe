package com.cafe.hopecafe;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class RegisterController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label registerMessageLabel;
    @FXML
    private Label confirmPasswordLabel;

    public void RegsiterButtonOnAction(){

        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            //confirmPasswordLabel.setText("you are set");
            registerUser();
            confirmPasswordLabel.setText("");
        }else{
            confirmPasswordLabel.setText("Password doesnt match");
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname=firstNameTextField.getText();
        String lastname=lastNameTextField.getText();
        String username=usernameTextField.getText();
        String password=setPasswordField.getText();

        String insertFields = "INSERT INTO user_account (first_name, last_name, username, password) VALUES ('";
        String insertValues = firstname +"','"+lastname +"','"+username +"','"+password +"')";
        String insertToRegister=insertFields+insertValues;

        try{

            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registerMessageLabel.setText("User Registered Successfully");

        }catch (Exception e){
                e.printStackTrace();
                e.getCause();
        }


    }


}
