package com.hfad.basicandroidlearningapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    public static final String EXTRA_TRANSFER_DATA = "transfer_data";
    public static final int REQUEST_CODE = 123;
    private final String LOG_TAG = "LifeCircle";

    private Button btnStartActivity;
    private Button btnStartActivityWithData;
    private Button btnUserImplicitIntent;
    private Button btnStartActivityForResult;
    private EditText editTxtTransferData;
    private TextView textViewReturnedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.i(LOG_TAG, "First Activity - onCreate");

        btnStartActivity = findViewById(R.id.button_start_second_activity);
        btnStartActivityWithData = findViewById(R.id.button_start_second_activity_with_data);
        btnUserImplicitIntent = findViewById(R.id.button_implicit_intent);
        btnStartActivityForResult = findViewById(R.id.button_start_activity_for_result);
        editTxtTransferData = findViewById(R.id.edit_transfer_data);
        textViewReturnedData = findViewById(R.id.text_view_result);

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
                startSecondActivityForResult();
            }
        });

        btnUserImplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImplicitIntentActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "First Activity - onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "First Activity - onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "First Activity - onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "First Activity - onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "First Activity - onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "First Activity - onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String result = data.getStringExtra(SecondActivity.EXTRA_RETURNED_DATA);
            textViewReturnedData.setText("Result: " + result);
        }
    }

    private void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void startSecondActivityWithData() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_TRANSFER_DATA, editTxtTransferData.getText());
        startActivity(intent);
    }
    private void startImplicitIntentActivity() {
        startActivity(new Intent(this, ImplicitIntentActivity.class));
    }

    private void startSecondActivityForResult() {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
}