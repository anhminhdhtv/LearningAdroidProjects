package com.example.learninglocalstorageapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user_table")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "day_of_birth")
    private Date dayOfBirth;

    @ColumnInfo(name = "email")
    private String userEmail;

    @ColumnInfo(name = "sex")
    private boolean man;

    @ColumnInfo(name = "address")
    private String userAddress;


    public UserInfo(@NonNull String userName, Date dayOfBirth, String userEmail, boolean man, String userAddress) {
        this.userName = userName;
        this.dayOfBirth = dayOfBirth;
        this.userEmail = userEmail;
        this.man = man;
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

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
