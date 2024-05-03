package com.cafe.hopecafe;

import com.cafe.hopecafe.utils.Routing;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

/**
 * This contains methods for user registration.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

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
    private TextField setPasswordField;
    @FXML
    private TextField confirmPasswordField;

    @FXML
    private Label registerMessageLabel;
    @FXML
    private Label confirmPasswordLabel;

    /**
     *  This method checks if both password input are the same.
     */

    public void RegisterButtonOnAction(){

        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            //confirmPasswordLabel.setText("you are set");
            registerUser();
            confirmPasswordLabel.setText("");
        }else{
            confirmPasswordLabel.setText("Password doesnt match");
        }
    }

    /**
     * This method is for user to cancel account registration.
     * @param event This parameter is left empty.
     */
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    /**
     * This method takes user input from registration and store into database.
     */

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname=firstNameTextField.getText();
        String lastname=lastNameTextField.getText();
        String username=usernameTextField.getText();
        String password=setPasswordField.getText();

        String insertFields = "INSERT INTO user_account (first_name, last_name, username, password) VALUES ('";
        String insertValues = firstname +"','" + lastname + "','" + username + "','"+ password + "')";
        String insertToRegister = insertFields + insertValues;

        try{

            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            //registerMessageLabel.setText("User Registered Successfully");


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "User Registered Successfully");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        Routing routing = new Routing();
                        routing.navigateToLoginPage();
                    });

        }catch (Exception e){
                e.printStackTrace();
                e.getCause();
        }


    }


}
