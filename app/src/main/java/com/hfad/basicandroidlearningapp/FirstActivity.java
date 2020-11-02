package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    private Button btnStartActivity;
    private Button btnStartActivityWithData;
    private Button btnUserImplicitIntent;
    private Button btnStartActivityForResult;
    private EditText editTxtTransferData;

    public static final String EXTRA_TRANSFER_DATA = "transfer_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnStartActivity = findViewById(R.id.button_start_second_activity);
        btnStartActivityWithData = findViewById(R.id.button_start_second_activity_with_data);
        btnUserImplicitIntent = findViewById(R.id.button_implicit_intent);
        btnStartActivityForResult = findViewById(R.id.button_start_activity_for_result);
        editTxtTransferData = findViewById(R.id.edit_transfer_data);

        // Set a click listener on the button
        btnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivity();
            }
        });

        btnStartActivityWithData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivityWithData();

            }
        });

        btnUserImplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivityWithData();
            }
        });

        btnStartActivityForResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void startSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void startSecondActivityWithData(){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_TRANSFER_DATA, editTxtTransferData.getText());
        startActivity(intent);
    }
}