package com.cafe.hopecafe.homePages;

import javafx.scene.control.CheckBox;

public class User {
    private String username;
    private String userType;
    private CheckBox checkBox;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}