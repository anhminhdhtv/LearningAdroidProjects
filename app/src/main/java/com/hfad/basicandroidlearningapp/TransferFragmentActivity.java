package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TransferFragmentActivity extends AppCompatActivity implements FragmentA.DataTransfer {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_fragment);
    }

    @Override
    public void sendData(String data) {
        FragmentB fragmentB =  (FragmentB)getSupportFragmentManager().findFragmentById(R.id.fragment_b);
        fragmentB.DisplayData(data);
    }
}