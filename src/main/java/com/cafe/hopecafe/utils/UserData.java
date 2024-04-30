package com.cafe.hopecafe.utils;

public class UserData {

    private static UserData instance;

    private String userName;
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

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
