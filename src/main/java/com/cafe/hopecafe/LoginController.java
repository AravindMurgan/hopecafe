package com.cafe.hopecafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

//    public Stage primaryStage;
//    public void setPrimaryStage(Stage primaryStage){
//        this.primaryStage=primaryStage;
//    }

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private BorderPane rootPane;
    @FXML
    private Hyperlink signUpField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
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
            validateLogin();
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

    public void validateLogin() throws SQLException, ClassNotFoundException {
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username='"+
                usernameTextField.getText() + "' AND password='"+passwordTextField .getText()+"'";

        try {
            Statement statement= connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            System.out.println(queryResult);
            System.out.println(queryResult);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    //loginMessageLabel.setText("Congrats");
                   // createAccountForm();
                }else{
                    loginMessageLabel.setText("Invalid user login, Please try again");
                }
            }

        }catch (Exception e){
                e.printStackTrace();
                e.getCause();
        }

    }

    public void signUpOnAction(){
        
        try {
            BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("register.fxml"));
            rootPane.getChildren().setAll(fxmlLoader);
         //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
////            Stage registerStage = new Stage();
//            Scene scene = new Scene(fxmlLoader.load(), 800, 427);
//            primaryStage.setTitle("Hope Cafe");
//            primaryStage.setScene(scene);
//            primaryStage.show();
        }catch (Exception e){
               e.printStackTrace();
               e.getCause();
        }
    }
}