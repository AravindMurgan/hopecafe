package com.cafe.hopecafe.orders;

import javafx.scene.control.CheckBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class creates ordered item cart.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */


public class OrderItem {

    private String orderId;
    private String customerName;
    private LocalDateTime orderDate;

    private String orderItemList;
    private String totalPrice;
    private String orderStatus;
    private String orderType;
    private String formattedOrderDate;

    private CheckBox checkBox;

    /**
     * This is a constructor for order check box parameters.
     * @param orderId pass order ID in
     * @param customerName pass customer's name in
     * @param orderItemList pass order list in
     * @param totalPrice pass total price in
     * @param orderStatus pass order status in
     * @param orderType pass order type in
     */

    public OrderItem(String orderId, String customerName,
                     String orderItemList, String totalPrice, String orderStatus, String orderType) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderItemList = orderItemList;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
    }

    /**
     * This is a constructor for order check box parameters.
     * @param orderId pass order ID in
     * @param customerName pass customer's name in
     * @param orderItemList pass order list in
     * @param totalPrice pass total price in
     * @param orderStatus pass order status in
     * @param orderType pass order type in
     * @param checkbox pass check box in
     */

    public OrderItem(String orderId, String customerName, String orderItemList,LocalDateTime orderDate,
                     String totalPrice, String orderStatus, String orderType,String checkbox) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderItemList = orderItemList;
        this.orderDate =orderDate;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderType = orderType;

        this.checkBox = new CheckBox();
        this.checkBox.setSelected(false);
        this.checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // You can perform any action here when checkbox state changes
            if (newValue) {
                System.out.println("Checkbox selected for: " + this.getOrderStatus() );
            } else {
                System.out.println("Checkbox deselected for: " + this.getOrderStatus());
            }
        });
    }

    /**
     * getter for FormattedOrderDate
     * @return value of FormattedOrderDate
     */


    public String getFormattedOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return orderDate.format(formatter);
    }

    /**
     * getter for TotalPrice
     * @return value of TotalPrice
     */


    public String getTotalPrice() {
        return totalPrice;
    }

    /**
     * setter for TotalPrice
     * @param totalPrice pass totalPrice from constructor
     */


    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * getter for order Date
     * @return value of order Date
     */


    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * setter for orderDate
     * @param orderDate pass orderDate from constructor
     */


    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * getter for CheckBox
     * @return value of CheckBox
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

    /**
     * getter for orderId
     * @return value of orderId
     */

    public String getOrderId() {
        return orderId;
    }

    /**
     * setter for orderId
     * @param orderId pass orderId from constructor
     */


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * getter for customerName
     * @return value of customerName
     */


    public String getCustomerName() {
        return customerName;
    }

    /**
     * setter for customerName
     * @param customerName pass customerName from constructor
     */


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * getter for orderItemList
     * @return value of orderItemList
     */


    public String getOrderItemList() {
        return orderItemList;
    }

    /**
     * setter for orderItemList
     * @param orderItemList pass orderItemList from constructor
     */


    public void setOrderItemList(String orderItemList) {
        this.orderItemList = orderItemList;
    }

    /**
     * getter for totalPrice
     * @return value of totalPrice
     */


    public String getItemPrice() {
        return totalPrice;
    }

    /**
     * setter for totalPrice
     * @param totalPrice pass totalPrice from constructor
     */


    public void setItemPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * getter for orderStatus
     * @return value of orderStatus
     */


    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * setter for orderStatus
     * @param orderStatus pass orderStatus from constructor
     */


    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * getter for orderType
     * @return value of orderType
     */


    public String getOrderType() {
        return orderType;
    }

    /**
     * setter for orderType
     * @param orderType pass orderType from constructor
     */


    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}