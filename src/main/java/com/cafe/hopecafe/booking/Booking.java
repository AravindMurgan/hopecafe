package com.cafe.hopecafe.booking;

import javafx.scene.control.CheckBox;

public class Booking {
    private int bookingId;
    private String tableNumber;
    private String date;
    private String time;
    private int noOfGuests;
    private int accountId;
    private String name;
    private String email;
    private String phone;
    private String bookingStatus;



    private CheckBox checkBox;

    public Booking(int bookingId, String tableNumber, String date, String time, int noOfGuests,
                   int accountId, String name, String email, String phone,String status,String checkbox) {
        this.bookingId = bookingId;
        this.tableNumber = tableNumber;
        this.date = date;
        this.time = time;
        this.noOfGuests = noOfGuests;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bookingStatus= status;

        this.checkBox = new CheckBox();
        this.checkBox.setSelected(false);
        this.checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // You can perform any action here when checkbox state changes
            if (newValue) {
                System.out.println("Checkbox selected for: " + this.getAccountId() + " " + this.getBookingStatus());
            } else {
                System.out.println("Checkbox deselected for: " + this.getAccountId()+ " " + this.getBookingStatus());
            }
        });
    }

    public Booking(int bookingId, String tableNumber, String date, String time, int noOfGuests,
                   int accountId, String name, String email, String phone,String status) {
        this.bookingId = bookingId;
        this.tableNumber = tableNumber;
        this.date = date;
        this.time = time;
        this.noOfGuests = noOfGuests;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bookingStatus= status;
    }

    public Booking() {

    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(int noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}