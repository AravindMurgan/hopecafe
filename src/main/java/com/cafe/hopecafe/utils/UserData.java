package com.cafe.hopecafe.utils;

/**
 * This class contains getter and setter for user data.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class UserData {

    private static UserData instance;

    private String userName;
    private String firstName;
    private String lastName;
    private Integer userid;

    /**
     * This private constructor is to prevent instantiation from outside.
     */

    public UserData() {}

    /**
     * This method is to get instance.
     * @return value of instance
     */

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public void clearUserData() {
        this.userName = null;
        this.firstName = null;
        this.lastName = null;
        this.userid = null;
    }
    /**
     * getter for userName
     * @return value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setter for userName
     * @param userName pass userName from constructor
     */


    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getter for userid
     * @return value of userid
     */


    public Integer getUserid() {
        return userid;
    }

    /**
     * getter for firstName
     * @return value of firstName
     */


    public String getFirstName() {
        return firstName;
    }

    /**
     * getter for lastName
     * @return value of lastName
     */


    public String getLastName() {
        return lastName;
    }

    /**
     * setter for userid
     * @param userid pass userid from constructor
     */


    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * setter for lastName
     * @param lastName pass lastName from constructor
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * setter for firstName
     * @param firstName pass firstName from constructor
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
