package com.cafe.hopecafe.homePages;


public class OrderType {
    private static OrderType instance;
    private String orderType;

    private OrderType() {
    }

    public static OrderType getInstance() {
        if (instance == null) {
            instance = new OrderType();
        }
        return instance;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}