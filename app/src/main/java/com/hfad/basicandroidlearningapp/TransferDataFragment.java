package com.hfad.basicandroidlearningapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TransferDataFragment extends Fragment implements FragmentA.DataTransfer {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.transfer_data_fragment, container, false);
    }

    @Override
    public void sendData(String data) {
        FragmentB fragmentB =  (FragmentB)getChildFragmentManager().findFragmentById(R.id.fragment_b);
        fragmentB.DisplayData(data);
    }
}
