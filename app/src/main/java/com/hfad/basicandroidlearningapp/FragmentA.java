package com.hfad.basicandroidlearningapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    private final String LOG_TAG = "FragmentLifeCircle";

    private TextView textViewReceivedData;
    private Button btnSendToActivity;
    private EditText editTextDataSendToActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        textViewReceivedData = view.findViewById(R.id.text_view_fragment_received_data);
        editTextDataSendToActivity = view.findViewById(R.id.edit_text_data_to_activity);
        btnSendToActivity = view.findViewById(R.id.btn_data_to_activity);

        btnSendToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textViewActivity =  getActivity().findViewById(R.id.text_view_activity_data);
                textViewActivity.setText("Data received from Fragment: " + editTextDataSendToActivity.getText());
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(LOG_TAG, "FragmentA - onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "FragmentA - onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "FragmentA - onActivityCreated");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "FragmentA - onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(LOG_TAG, "FragmentA - onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "FragmentA - onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(LOG_TAG, "FragmentA - onDetach");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "FragmentA - onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "FragmentA - onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "FragmentA - onResume");
    }

    public void SetData(String data){
        textViewReceivedData.setText("Data received from Activity: " + data);
    }
}
