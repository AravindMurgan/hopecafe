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

/**
 * This class is to create staff control pane and methods to add staffs.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

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
    private Label confirmPasswordLabel;

    @FXML
    private TextField staffTypeTextField;

    /**
     * This method is to check registering staff's password meets requirements.
     */

    public void RegisterButtonOnAction(){

        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            confirmPasswordLabel.setText("");
        }else{
            confirmPasswordLabel.setText("Password doesnt match");
        }
    }

    /**
     * This method is to set cancel registration button.
     * @param event left empty
     */
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    /**
     * This method is to update new staff into database.
     */

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

    /**
     * This method is to go back to manager page.
     */

    public void goBackOnAction(){
        Routing routing = new Routing();
        routing.navigateToPage(FxmlPaths.MANAGER_VIEW_FXML);
    }


}
