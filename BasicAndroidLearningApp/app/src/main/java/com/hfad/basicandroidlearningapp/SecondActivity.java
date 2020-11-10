package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_RETURNED_DATA = "returned_data";

    private TextView textViewReceivedData;
    private TextView textViewReturnedData;
    private Button btnBack;
    private final String LOG_TAG = "LifeCircle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i(LOG_TAG, "Second Activity - onCreate");

        textViewReceivedData = findViewById(R.id.text_view_received_data);
        textViewReturnedData = findViewById(R.id.edit_txt_returned_data);
        btnBack = findViewById(R.id.button_back);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            // In case start a Activity with data
            textViewReturnedData.setVisibility(View.INVISIBLE);
            btnBack.setVisibility(View.INVISIBLE);
            textViewReceivedData.setText("Received Data: " + bundle.get(FirstActivity.EXTRA_TRANSFER_DATA));
        }
        else {
            textViewReceivedData.setVisibility(View.INVISIBLE);

            if(getCallingActivity() == null){
                // In case start a Activity without do anything
                textViewReturnedData.setVisibility(View.INVISIBLE);
                btnBack.setVisibility(View.INVISIBLE);
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnedData = textViewReturnedData.getText().toString();

                if (returnedData == null || returnedData.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Data not filled. Please input and try again!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    Intent data = new Intent();
                    data.putExtra(EXTRA_RETURNED_DATA, returnedData);
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "Second Activity - onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "Second Activity - onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "Second Activity - onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "Second Activity - onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "Second Activity - onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "Second Activity - onDestroy");
    }
}