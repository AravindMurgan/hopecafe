package com.cafe.hopecafe.utils;

public class UserData {

    private static UserData instance;

    private String userName;
    private String firstName;
    private String lastName;
    private Integer userid;

    public UserData() {
        // Private constructor to prevent instantiation from outside
    }

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
