package com.cafe.hopecafe.booking;

import javafx.scene.control.CheckBox;

/**
 * This contains getter and setter for booking page information.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

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

    /**
     * This is constructor for booking check box parameters.
     * @param bookingId Pass bookingID in.
     * @param tableNumber Pass table number in.
     * @param date Pass date in.
     * @param time Pass time in.
     * @param noOfGuests Pass number of guests in.
     * @param accountId Pass user account ID in.
     * @param name Pass username in.
     * @param email Pass user email in.
     * @param phone Pass user phone number in.
     * @param status Pass booking status in.
     * @param checkbox Pass booking checkbox in.
     */

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

    /**
     * This is constructor for booking information.
     * @param bookingId Pass bookingID in.
     * @param tableNumber Pass table number in.
     * @param date Pass date in.
     * @param time Pass time in.
     * @param noOfGuests Pass number of guests in.
     * @param accountId Pass user account ID in.
     * @param name Pass username in.
     * @param email Pass user email in.
     * @param phone Pass user phone number in.
     * @param status Pass booking status in.
     */

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

    /**
     * This is an empty constructor.
     */

    public Booking() {

    }

    // Getters and Setters

    /**
     * getter for check box
     * @return value of check box
     */
    public CheckBox getCheckBox() {
        return checkBox;
    }

    /**
     * setter for check box
     * @param checkBox pass in check box from constructor
     */
    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    /**
     * getter for booking status
     * @return value of booking status
     */
    public String getBookingStatus() {
        return bookingStatus;
    }

    /**
     * setter for booking status
     * @param bookingStatus pass in booking status from constructor
     */
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    /**
     * getter for booking ID
     * @return value of booking ID
     */

    public int getBookingId() {
        return bookingId;
    }

    /**
     * setter for booking ID
     * @param bookingId pass in booking from constructor
     */

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * getter for table number
     * @return value of booking number
     */

    public String getTableNumber() {
        return tableNumber;
    }

    /**
     * setter for table number
     * @param tableNumber pass in table number from constructor
     */

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * getter for date
     * @return value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * setter for booking date
     * @param date pass in date from constructor
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * getter for booking time
     * @return value of booking time
     */

    public String getTime() {
        return time;
    }

    /**
     * setter for booking time
     * @param time value of booking time
     */

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * getter for number of guests
     * @return value of number of guests
     */

    public int getNoOfGuests() {
        return noOfGuests;
    }

    /**
     * setter for number of guests
     * @param noOfGuests pass in of number of guests from constructor
     */

    public void setNoOfGuests(int noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    /**
     * getter for account ID
     * @return value of number of guests
     */

    public int getAccountId() {
        return accountId;
    }

    /**
     * setter for account ID
     * @param accountId pass in of account ID from constructor
     */

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * getter for user's name
     * @return value of user's name
     */

    public String getName() {
        return name;
    }

    /**
     * setter for user's name
     * @param name pass in user's name from constructor
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for user's email
     * @return value of user's email
     */

    public String getEmail() {
        return email;
    }

    /**
     * setter for user's email
     * @param email pass in user's email from constructor
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for user's phone number
     * @return value of user's phone number
     */

    public String getPhone() {
        return phone;
    }

    /**
     * setter for user's phone number
     * @param phone pass in user's phone number from constructor
     */

    public void setPhone(String phone) {
        this.phone = phone;
    }
}