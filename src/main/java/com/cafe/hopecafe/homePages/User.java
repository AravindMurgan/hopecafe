package com.cafe.hopecafe.homePages;

import javafx.scene.control.CheckBox;

/**
 * This class is to create user information and store in database.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class User {
    private String username;
    private String userType;
    private CheckBox checkBox;

    /**
     * This is a constructor for user class.
     * @param username set username
     * @param userType set user type
     * @param checkBoxText set check box text
     */

    public User(String username, String userType, String checkBoxText) {
        this.username = username;
        this.userType = userType;
        this.checkBox = new CheckBox();
        this.checkBox.setSelected(false);
        this.checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // You can perform any action here when checkbox state changes
            if (newValue) {
                System.out.println("Checkbox selected for: " + this.getUsername() + " " + this.getUserType());
            } else {
                System.out.println("Checkbox deselected for: " +  this.getUsername() + " " + this.getUserType());
            }
        });
    }

    /**
     * getter for username
     * @return value of username
     */


    public String getUsername() {
        return username;
    }

    /**
     * setter for username
     * @param username pass username from constructor
     */


    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for userType
     * @return value of userType
     */


    public String getUserType() {
        return userType;
    }

    /**
     * setter for userType
     * @param userType pass userType from constructor
     */


    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * getter for checkBox
     * @return value of checkBox
     */


    public CheckBox getCheckBox() {
        return checkBox;
    }

    /**
     * setter for checkBox
     * @param checkBox pass checkBox from constructor
     */


    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}