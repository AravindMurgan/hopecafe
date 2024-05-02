package com.cafe.hopecafe;

import com.cafe.hopecafe.utils.FxmlLoaderUtil;
import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.RootBorderPaneHolder;
import com.cafe.hopecafe.utils.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    String username= usernameTextField.getText();
    String password= passwordTextField.getText();

    String verifyLogin = "SELECT * FROM user_account WHERE username='"+
            username + "' AND password='"+password+"'";

    try {
        Statement statement= connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLogin);

        if(queryResult.next()){
            int userId = queryResult.getInt("account_id");
            String firsName = queryResult.getString("first_name");
            String lastName = queryResult.getString("last_name");
            String userType = queryResult.getString("user_type"); // Assuming you have a user_type column in your user_account table

            UserData.getInstance().setUserid(userId);
            UserData.getInstance().setFirstName(firsName);
            UserData.getInstance().setLastName(lastName);
            RootBorderPaneHolder.getInstance().setRootPane(rootPane);

            String fxmlPath;
            if ("manager".equalsIgnoreCase(userType)) {
                fxmlPath = FxmlPaths.MANAGER_VIEW_FXML;
            } else if ("waiter".equalsIgnoreCase(userType)) {
                fxmlPath = FxmlPaths.WAITER_VIEW_FXML;
            } else {
                fxmlPath = FxmlPaths.CUSTOMER_HOME_PAGE;
            }

            try {
                AnchorPane fxmlLoader = FXMLLoader.load(getClass().getResource(fxmlPath));
                rootPane.getChildren().setAll(fxmlLoader);
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }else{
            loginMessageLabel.setText("Invalid user login, Please try again");
        }

    }catch (Exception e){
        e.printStackTrace();
        e.getCause();
    }
}
    public void signUpOnAction(){

        try {
            BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource(FxmlPaths.REGISTER_FXML));
            rootPane.getChildren().setAll(fxmlLoader);
        }catch (Exception e){
               e.printStackTrace();
               e.getCause();
        }
    }
}