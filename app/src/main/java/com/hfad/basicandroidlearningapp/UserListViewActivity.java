package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class UserListViewActivity extends AppCompatActivity {

    private ArrayList<UserInfo> userInfos;
    private UserListViewAdapter userListViewAdapter;
    private ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);

        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("asdad", "SDSDD", "SDADADA", true));
        userInfos.add(new UserInfo("asdad", "SDSDD", "SDADADA", false));
        userInfos.add(new UserInfo("asdad", "SDSDD", "SDADADA", true));
        userInfos.add(new UserInfo("asdad", "SDSDD", "SDADADA", false));
        userInfos.add(new UserInfo("asdad", "SDSDD", "SDADADA", true));
        userInfos.add(new UserInfo("asdad", "SDSDD", "SDADADA", false));

        userListViewAdapter = new UserListViewAdapter(userInfos);
        listViewUsers = findViewById(R.id.list_view_user);
        listViewUsers.setAdapter(userListViewAdapter);
    }
}