package com.cafe.hopecafe.orders;

import javafx.scene.control.CheckBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class OrderItem {

    private String orderId;
    private String customerName;
    private LocalDateTime orderDate;

    private String orderItemList;
    private String totalPrice;
    private String orderStatus;
    private String orderType;
    private String formattedOrderDate;


    public String getFormattedOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return orderDate.format(formatter);
    }



    private CheckBox checkBox;

    public OrderItem(String orderId, String customerName,
                     String orderItemList, String totalPrice, String orderStatus, String orderType) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderItemList = orderItemList;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
    }

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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(String orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getItemPrice() {
        return totalPrice;
    }

    public void setItemPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}