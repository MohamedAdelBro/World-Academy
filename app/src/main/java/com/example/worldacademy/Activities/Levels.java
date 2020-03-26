package com.example.worldacademy.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;

import com.example.worldacademy.Fragments.Adapters.Levels_Aapter;
import com.example.worldacademy.Fragments.Models.LevelsModel;
import com.example.worldacademy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Levels extends AppCompatActivity {
    DatabaseReference mDatabaseReference;
    RecyclerView mRecyclerView;
    public static String mItemId;
    Intent mIntent;
    Levels_Aapter mLevels_aapter;
    ArrayList<LevelsModel> mModel = new ArrayList<>();
    LevelsModel mLevelsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        InstViews();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Courses").child(mItemId).child("Folders");
        GetFolder();
    }

    //----------------------------------------------------------------------------------------------
    private void GetFolder() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mModel.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    mLevelsModel = postSnapshot.getValue(LevelsModel.class);
                    mModel.add(mLevelsModel);
                }
                mLevels_aapter = new Levels_Aapter(mModel, Levels.this);
                mRecyclerView.setAdapter(mLevels_aapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //----------------------------------------------------------------------------------------------
    private void InstViews() {
        mIntent = getIntent();
        mItemId = mIntent.getStringExtra("ItemId");

        mRecyclerView = findViewById(R.id.Rec);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);

    }
}
