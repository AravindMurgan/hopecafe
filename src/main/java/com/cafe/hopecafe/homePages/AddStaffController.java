package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.Routing;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class AddStaffController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private MFXPasswordField setPasswordField;
    @FXML
    private MFXPasswordField confirmPasswordField;

    @FXML
    private Label registerMessageLabel;
    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField staffTypeTextField;

    public void RegisterButtonOnAction(){

        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
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
        String staffType=staffTypeTextField.getText();

        String insertFields = "INSERT INTO user_account (first_name, last_name, username, password, user_type) VALUES ('";
        String insertValues = firstname +"','"+lastname +"','"+username +"','"+password +"','"+staffType +"')";
        String insertToRegister = insertFields + insertValues;

        try{

            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

//            registerMessageLabel.setText("User Registered Successfully");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Staff Registered Successfully");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        Routing routing = new Routing();
                        routing.navigateToPage(FxmlPaths.MANAGER_VIEW_FXML);
                    });

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

    public void goBackOnAction(){
        Routing routing = new Routing();
        routing.navigateToPage(FxmlPaths.MANAGER_VIEW_FXML);
    }


}
