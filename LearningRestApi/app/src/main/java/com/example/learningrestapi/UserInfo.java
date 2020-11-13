package com.example.learningrestapi;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    private int id;

    @SerializedName("name")
    private String userName;

    @SerializedName("day_of_birth")
    private Long dayOfBirth;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("sex")
    private boolean male;

    @SerializedName("address")
    private String userAddress;


    public UserInfo(String userName, Long dayOfBirth, String userEmail, boolean male, String userAddress) {
        this.userName = userName;
        this.dayOfBirth = dayOfBirth;
        this.userEmail = userEmail;
        this.male = male;
        this.userAddress = userAddress;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Long dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
