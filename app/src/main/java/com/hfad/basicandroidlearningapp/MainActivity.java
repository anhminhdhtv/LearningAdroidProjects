package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnActivities;
    private Button btnFragments;
    private Button btnUiControls;
    private Button btnListView;
    private Button btnRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivities = findViewById(R.id.button_activity);
        btnFragments = findViewById(R.id.button_fragments);
        btnUiControls = findViewById(R.id.button_ui_controls);
        btnListView = findViewById(R.id.button_list_view);
        btnRecyclerView = findViewById(R.id.button_recycler_view);

        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FirstActivity.class));
            }
        });

        btnFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FragmentsActivity.class));
            }
        });

        btnUiControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UiControlsActivity.class));
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserListViewActivity.class));
            }
        });

        btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserRecyclerViewActivity.class));
            }
        });


    }
}