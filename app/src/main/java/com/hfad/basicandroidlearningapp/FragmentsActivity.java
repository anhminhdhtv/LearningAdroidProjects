package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private EditText editTextActivityData;
    private Button btnSendDataToFragment;
    private Button btnTransferDataFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        editTextActivityData = findViewById(R.id.edit_text_data_to_fragment);
        btnSendDataToFragment = findViewById(R.id.button_send_data_fragment);
        btnTransferDataFragment = findViewById(R.id.button_transfer_data_fragment);

        btnSendDataToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = (FragmentA) fragmentManager.findFragmentByTag("TAG_FRAGMENT_A");
                if (fragmentA == null){
                    Toast.makeText(getApplicationContext(), "Fragment A not exist", Toast.LENGTH_SHORT);
                }
                else {
                    fragmentA.SetData(editTextActivityData.getText().toString());
                }
            }
        });
    }

    public void AddFragment(View view) {
        switch (view.getId()) {
            case R.id.button_add_fragment_a:
                addA();
                break;

            case R.id.button_add_fragment_b:
                addB();
                break;
        }
    }

    public void RemoveFragment(View view) {
        switch (view.getId()) {
            case R.id.button_remove_fragment_a:
                removeA();
                break;

            case R.id.button_remove_fragment_b:
                removeB();
                break;
        }
    }

    public void ReplaceFragment(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new FragmentB(), "TAG_FRAGMENT_B");
        fragmentTransaction.addToBackStack("fragmentB");
        fragmentTransaction.commit();
    }

    public void BackFragment(View view){
        fragmentManager.popBackStack();
    }

    public void PopFragment(View view){
        fragmentManager.popBackStack("fragmentA", 0);
    }

    private void addA() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, new FragmentA(), "TAG_FRAGMENT_A");
        fragmentTransaction.addToBackStack("fragmentA");
        fragmentTransaction.commit();
    }

    private void addB() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, new FragmentB(), "TAG_FRAGMENT_B");
        fragmentTransaction.addToBackStack("fragmentB");
        fragmentTransaction.commit();
    }

    private void removeA() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = (FragmentA) fragmentManager.findFragmentByTag("TAG_FRAGMENT_A");
        if (fragmentA != null) {
            fragmentTransaction.remove(fragmentA);
            fragmentTransaction.commit();
        }
    }

    private void removeB() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentB fragmentB = (FragmentB) fragmentManager.findFragmentByTag("TAG_FRAGMENT_B");
        if (fragmentB != null) {
            fragmentTransaction.remove(fragmentB);
            fragmentTransaction.commit();
        }
    }

    public void AddTransferFragment(View view){
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frame_layout, new TransferDataFragment(), "TAG_TRANSFER_FRAGMENT");
//
//        fragmentTransaction.commit();
        startActivity(new Intent(this, TransferFragmentActivity.class));
    }
}