package com.cafe.hopecafe.booking;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.Routing;
import com.cafe.hopecafe.utils.RootBorderPaneHolder;
import com.cafe.hopecafe.utils.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * This contains methods for booking page control.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class CreateBookingController {

    @FXML
    private DatePicker dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField noofguestsField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private Label  validationLabel;
    LocalDate selectedDate;

    /**
     * initializer for booking date
     */

    public void initialize() {
        dateField.setOnAction(e -> {
            selectedDate = dateField.getValue();
            System.out.println("Selected date: " + selectedDate);
            // Optionally, you can perform further actions with the selected date here
        });
    }

    /**
     * getter for date selected by user
     * @return value of date selected by user
     */

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    /**
     * This method defines the function of create booking button.
     */


    public void createBookingButtonOnAction(){
        if(dateField.getValue() == null){
            validationLabel.setText("All fields are required.");
            return;
        }
        String formattedDate = getSelectedDate().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));

        String time = timeField.getText();
        String noofguests=noofguestsField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneno=phoneField.getText();


        // Perform validation
        if (  time.isEmpty() || noofguests.isEmpty()
                || name.isEmpty() || email.isEmpty() || formattedDate.isEmpty()
        || phoneno.isEmpty()) {
            validationLabel.setText("All fields are required.");
        }else{
            validationLabel.setText("");
            createBookingForUser();
        }
    }

    /**
     * This method is for booking creation, used in createBookingButtonOnAction method
     * @see #createBookingButtonOnAction()
     */
    public void createBookingForUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String formattedDate = getSelectedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String time = timeField.getText();
        int noofguests=Integer.parseInt(noofguestsField.getText()) ;
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneno= phoneField.getText();
        Integer user_id = UserData.getInstance().getUserid();
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;
        String tableNumber = "Table" + randomNum;

        String insertFields = "INSERT INTO bookings (booking_status,table_number, date, time, no_of_guests,account_id,name,email,phone) VALUES (";
        String insertValues = "'PENDING', '"+tableNumber+"', '"+formattedDate+"', '"+time +"', '"+noofguests +"', '"+user_id +"', '"+name+"', '"+email+"', '"+phoneno +"'";
        String insertToRegister=insertFields+insertValues+")";

        try{
            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Booking created successfully");
            BorderPane rootPane = RootBorderPaneHolder.getInstance().getRootPane();
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        try {
                            AnchorPane fxmlLoader = FXMLLoader.load(getClass().getResource(FxmlPaths.CUSTOMER_HOME_PAGE));
                            rootPane.getChildren().setAll(fxmlLoader);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * This method direct user back to customer's home page on click.
     */
    public void cancelButtonOnAction(){
        new Routing().navigateToHomePage(FxmlPaths.CUSTOMER_HOME_PAGE);
    }

}
