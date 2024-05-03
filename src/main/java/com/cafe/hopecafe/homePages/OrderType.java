package com.cafe.hopecafe.homePages;

/**
 * This class is for user to select order type.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */


public class OrderType {
    private static OrderType instance;
    private String orderType;

    private OrderType() {
    }

    /**
     * This method is to get instance of order type.
     * @return value of instance
     */

    public static OrderType getInstance() {
        if (instance == null) {
            instance = new OrderType();
        }
        return instance;
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