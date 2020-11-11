package com.example.learninglocalstorageapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRecyclerViewActivity extends AppCompatActivity {

    private ArrayList<UserInfo> userInfos;
    private RecyclerView recyclerView;
    UserRecyclerViewAdapter userRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recycler_view);

        recyclerView = findViewById(R.id.recycler_view_user);

        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("Nguyen Van A", "12/11/1996", "nguyenvana@gmail.com", true));
        userInfos.add(new UserInfo("Nguyen Van B", "12/11/1996", "nguyenvanb@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van C", "12/11/1996", "nguyenvanc@gmail.com", true));
        userInfos.add(new UserInfo("Nguyen Van D", "12/11/1996", "nguyenvand@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van E", "12/11/1996", "nguyenvane@gmail.com", true));
        userInfos.add(new UserInfo("Nguyen Van F", "12/11/1996", "nguyenvanf@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van G", "12/11/1996", "nguyenvang@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van H", "12/11/1996", "nguyenvanh@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van I", "12/11/1996", "nguyenvani@gmail.com", false));

        userRecyclerViewAdapter = new UserRecyclerViewAdapter(userInfos);
        recyclerView.setAdapter(userRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}