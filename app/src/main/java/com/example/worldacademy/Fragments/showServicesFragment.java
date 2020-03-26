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
public class showServicesFragment extends AppCompatDialogFragment {


    View view ;

    public showServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_show_services, container, false);
        return view ;

    }

}
