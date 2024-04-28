package com.cafe.hopecafe.booking;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


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

    public void initialize() {
        dateField.setOnAction(e -> {
            selectedDate = dateField.getValue();
            System.out.println("Selected date: " + selectedDate);
            // Optionally, you can perform further actions with the selected date here
        });
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }


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
    public void createBookingForUser(){

        System.out.println("booking created");

    }

}
