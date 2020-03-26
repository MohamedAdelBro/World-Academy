package com.example.worldacademy.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.worldacademy.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class orderPlacedFragment extends Fragment implements View.OnClickListener {


    View view ;

    ImageView exitimg ;

    public orderPlacedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_order_placed, container, false);
        initViews();
        return view ;
    }

    private void initViews() {

        exitimg = view.findViewById(R.id.mclose_icon_orderPlacedf);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {



            case R.id.mclose_icon_orderPlacedf:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.morderPlacedPageContainer , new homeFragment()).commit();
                break;

        }

    }
}
