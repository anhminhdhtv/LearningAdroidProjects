package com.example.learningrestapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Mock server Url
    public static final String BASE_URL = "https://5fa8f65ac9b4e90016e69cdc.mockapi.io/";

    private UserInfoAdapter userInfoAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_user);
        ImageButton btnAddUser = findViewById(R.id.button_add_user);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
                intent.putExtra(UserFormActivity.EXTRA_START_MODE, UserFormActivity.VALUE_START_MODE_INSERT);
                startActivity(intent);
                finish();
            }
        });

        userInfoAdapter = new UserInfoAdapter(this);
        recyclerView.setAdapter(userInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getAllUserInfo();
    }

    private void getAllUserInfo(){
        // url to get all User info
        String url = BASE_URL+ "api/users";

        // Create headers
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        GsonRequest<UserInfo[]> userInfoList = new GsonRequest<>(Request.Method.GET,
                url, UserInfo[].class, headers, new Response.Listener<UserInfo[]>() {
            @Override
            public void onResponse(UserInfo[] response) {
                userInfoAdapter.SetUserList(Arrays.asList(response));
            }
        }, errorListener);

        SingletonRequestQueue.getInstance(this).addToRequestQueue(userInfoList);
    }

    public void updateUserInfo(UserInfo userInfo) {
        String stringUerInfo = new Gson().toJson(userInfo);
        Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
        intent.putExtra(UserFormActivity.EXTRA_START_MODE, UserFormActivity.VALUE_START_MODE_UPDATE);
        intent.putExtra(UserFormActivity.EXTRA_UPDATED_USER, stringUerInfo);
        startActivity(intent);
        finish();
    }

    public void deleteUser(int userId){

        String url = String.format(BASE_URL + "api/users/%s", String.valueOf(userId));

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // refresh data
                        getAllUserInfo();
                    }
                },
                errorListener);
        SingletonRequestQueue.getInstance(this).addToRequestQueue(stringRequest);
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };
}