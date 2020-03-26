package com.example.worldacademy.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Adapters.SucessAdapter;
import com.example.worldacademy.Fragments.Models.CartModel;
import com.example.worldacademy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaildOrder extends AppCompatDialogFragment {
View view;
RecyclerView recyclerView;
ArrayList<CartModel> cartModels = new ArrayList<>();
DatabaseReference mDatabaseReference;
SessionMangment mangment;
    public FaildOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.faildorderfragment, container, false);
        mangment = new SessionMangment(getContext());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Faild Order").child(mangment.getUserDetails().get(mangment.KEY_ID));
        InstViews();
Load();
        return view;
    }

    private void InstViews() {
        recyclerView = view.findViewById(R.id.RecVer);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    void Load (){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    CartModel model = postSnapshot.getValue(CartModel.class);
                    cartModels.add(model);
                }

                SucessAdapter cartAdapter = new SucessAdapter(cartModels , getActivity());
                recyclerView.setAdapter(cartAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
