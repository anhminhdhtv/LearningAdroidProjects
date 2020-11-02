package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewReceivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textViewReceivedData = findViewById(R.id.text_view_received_data);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            textViewReceivedData.setText("Received Data: " + bundle.get(FirstActivity.EXTRA_TRANSFER_DATA));
        }
    }
}