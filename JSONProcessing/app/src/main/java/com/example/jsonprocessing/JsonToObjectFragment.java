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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonToObjectFragment extends Fragment {

    private UserInfoAdapter userInfoAdapter;
    private ArrayList<UserInfo> userInfos;

    private EditText editTextJson;
    private Button btnConvert;
    private TextView textViewObject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_json_to_object, container, false);

        // Mapping
        editTextJson = view.findViewById(R.id.edit_text_json);
        btnConvert = view.findViewById(R.id.btn_ob_to_json);
        textViewObject = view.findViewById(R.id.text_view_object);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_user);


        // Define Recycler view
        userInfos = new ArrayList<>();
        userInfoAdapter = new UserInfoAdapter(userInfos);
        recyclerView.setAdapter(userInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get convert mode
        String convertMode = this.getArguments().getString(MainActivity.ARGUMENT_COVERT_MODE);

        if(convertMode.equals("JsonToObject")){

            recyclerView.setVisibility(View.GONE);
            textViewObject.setVisibility(View.VISIBLE);
            btnConvert.setText("Convert to Object");

            btnConvert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gson gson = new Gson();
                    UserInfo userInfo = gson.fromJson(editTextJson.getText().toString(), UserInfo.class);
                    textViewObject.setText(String.format("Name: %s \n Age: %s \n Email: %s", userInfo.getUserName(), userInfo.getUserAge(), userInfo.getUserEmail()));
                }
            });
        }
        else {

            recyclerView.setVisibility(View.VISIBLE);
            textViewObject.setVisibility(View.GONE);
            btnConvert.setText("Convert to List");

            btnConvert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Clear current data
                    userInfos.clear();

                    // Get user list from json
                    Type UserListType = new TypeToken<ArrayList<UserInfo>>(){}.getType();
                    ArrayList<UserInfo> newUserList = new Gson().fromJson(editTextJson.getText().toString(), UserListType);

                    // Update new list
                    userInfos.addAll(newUserList);
                    userInfoAdapter.notifyDataSetChanged();
                }
            });
        }
        return view;
    }
}
