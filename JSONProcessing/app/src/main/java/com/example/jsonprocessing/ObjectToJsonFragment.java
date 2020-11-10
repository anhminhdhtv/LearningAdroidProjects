package com.example.jsonprocessing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ObjectToJsonFragment extends Fragment {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextEmail;
    private Button btnConvert;
    private TextView textViewJson;

    private ArrayList<UserInfo> userInfos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_object_to_json, container, false);

        // Mapping
        editTextName = view.findViewById(R.id.edit_text_user_name);
        editTextAge = view.findViewById(R.id.edit_text_user_age);
        editTextEmail = view.findViewById(R.id.edit_text_user_email);
        textViewJson = view.findViewById(R.id.text_view_json);
        btnConvert = view.findViewById(R.id.button_convert);

        userInfos = new ArrayList<>();

        // Get convert mode
        String convertMode = this.getArguments().getString(MainActivity.ARGUMENT_COVERT_MODE);

        if (convertMode.equals("ObjectToJson")) {

            btnConvert.setText("Convert");
            btnConvert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserInfo userInfo = new UserInfo(editTextName.getText().toString(), Integer.parseInt(editTextAge.getText().toString()), editTextEmail.getText().toString());
                    String userJson = new Gson().toJson(userInfo);
                    textViewJson.setText(userJson);
                }
            });
        } else {

            btnConvert.setText("Add to List");
            btnConvert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Add new data to List
                    UserInfo userInfo = new UserInfo(editTextName.getText().toString(), Integer.parseInt(editTextAge.getText().toString()), editTextEmail.getText().toString());
                    userInfos.add(userInfo);

                    // Convert new List  to Json
                    String userJson = new Gson().toJson(userInfos);
                    textViewJson.setText(userJson);
                }
            });
        }
        return view;
    }
}
