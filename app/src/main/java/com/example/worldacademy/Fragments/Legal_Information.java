package com.example.worldacademy.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import com.example.worldacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Legal_Information extends AppCompatDialogFragment {
View view;

    public Legal_Information() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_legal, container, false);

        return view;
    }

}
