package com.example.jsonprocessing;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo {
    @SerializedName("name")
    private String userName;

    @SerializedName("age")
    private int userAge;

    @SerializedName("email")
    private String userEmail;
}
