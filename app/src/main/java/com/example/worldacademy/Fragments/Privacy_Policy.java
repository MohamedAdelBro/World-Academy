package com.example.worldacademy.Fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worldacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Privacy_Policy extends AppCompatDialogFragment {
View view;

    public Privacy_Policy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_privacy__policy, container, false);

        return view;
    }

}
