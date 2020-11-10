package com.hfad.basicandroidlearningapp;

public class UserInfo {
    private String userName;
    private String dayOfBirth;
    private String userEmail;
    private boolean man;

    public UserInfo(String userName, String dayOfBirth, String userEmail, boolean man) {
        this.userName = userName;
        this.dayOfBirth = dayOfBirth;
        this.userEmail = userEmail;
        this.man = man;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
