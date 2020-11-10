package com.example.jsonprocessing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    public static final String ARGUMENT_COVERT_MODE = "CONVERT_MODE";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
    }

    public void ButtonClick(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (view.getId()){
            case R.id.button_object_to_json:
                // Start a Fragment to convert from Object to Json
                ObjectToJsonFragment objectToJsonFragment = new ObjectToJsonFragment();
                Bundle objectToJsonFragmentArgs = new Bundle();
                objectToJsonFragmentArgs.putString(ARGUMENT_COVERT_MODE, "ObjectToJson");
                objectToJsonFragment.setArguments(objectToJsonFragmentArgs);
                fragmentTransaction.replace( R.id.frame_main, objectToJsonFragment);
                fragmentTransaction.commit();
                break;

            case R.id.button_json_to_object:
                // Start a Fragment to convert from Json to Object
                JsonToObjectFragment jsonToObjectFragment = new JsonToObjectFragment();
                Bundle jsonToObjectFragmentArgs = new Bundle();
                jsonToObjectFragmentArgs.putString(ARGUMENT_COVERT_MODE, "JsonToObject");
                jsonToObjectFragment.setArguments(jsonToObjectFragmentArgs);
                fragmentTransaction.replace( R.id.frame_main, jsonToObjectFragment);
                fragmentTransaction.commit();
                break;

            case R.id.button_list_to_json:
                // Start a Fragment to convert from List to Json
                fragmentTransaction.replace( R.id.frame_main, new ObjectToJsonFragment());
                ObjectToJsonFragment listToJsonFragment = new ObjectToJsonFragment();
                Bundle lisToJsonFragmentArgs = new Bundle();
                lisToJsonFragmentArgs.putString(ARGUMENT_COVERT_MODE, "ListToJson");
                listToJsonFragment.setArguments(lisToJsonFragmentArgs);
                fragmentTransaction.replace( R.id.frame_main, listToJsonFragment);
                fragmentTransaction.commit();
                break;

            case R.id.button_json_to_list:
                // Start a Fragment to convert from Json to List
                fragmentTransaction.replace( R.id.frame_main,new JsonToObjectFragment());
                JsonToObjectFragment jsonToListFragment = new JsonToObjectFragment();
                Bundle jsonToListFragmentArgs = new Bundle();
                jsonToListFragmentArgs.putString(ARGUMENT_COVERT_MODE, "JsonToList");
                jsonToListFragment.setArguments(jsonToListFragmentArgs);
                fragmentTransaction.replace( R.id.frame_main, jsonToListFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}