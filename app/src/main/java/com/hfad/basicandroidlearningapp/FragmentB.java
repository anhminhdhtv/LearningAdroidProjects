package com.hfad.basicandroidlearningapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    private String LOG_TAG = "FragmentLifeCircle";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(LOG_TAG, "FragmentB - onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "FragmentB - onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "FragmentB - onActivityCreated");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "FragmentB - onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(LOG_TAG, "FragmentB - onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "FragmentB - onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(LOG_TAG, "FragmentB - onDetach");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "FragmentB - onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "FragmentB - onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "FragmentB - onResume");
    }
}
