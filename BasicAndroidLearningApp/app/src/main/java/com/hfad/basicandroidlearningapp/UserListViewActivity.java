package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        userInfos.add(new UserInfo("Nguyen Van A", "12/11/1996", "nguyenvana@gmail.com", true));
        userInfos.add(new UserInfo("Nguyen Van B", "12/11/1996", "nguyenvanb@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van C", "12/11/1996", "nguyenvanc@gmail.com", true));
        userInfos.add(new UserInfo("Nguyen Van D", "12/11/1996", "nguyenvand@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van E", "12/11/1996", "nguyenvane@gmail.com", true));
        userInfos.add(new UserInfo("Nguyen Van F", "12/11/1996", "nguyenvanf@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van G", "12/11/1996", "nguyenvang@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van H", "12/11/1996", "nguyenvanh@gmail.com", false));
        userInfos.add(new UserInfo("Nguyen Van I", "12/11/1996", "nguyenvani@gmail.com", false));

        userListViewAdapter = new UserListViewAdapter(userInfos);
        listViewUsers = findViewById(R.id.list_view_user);
        listViewUsers.setAdapter(userListViewAdapter);

        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), userInfos.get(i).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}