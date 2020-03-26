package com.example.worldacademy.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import com.example.worldacademy.Fragments.Adapters.VideoAdapter;
import com.example.worldacademy.Fragments.Models.VideoModel;
import com.example.worldacademy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.worldacademy.Activities.Levels.mItemId;

public class Video extends AppCompatActivity {

    RecyclerView mRecyclerView;
    VideoModel videoModel;
    DatabaseReference mDatabaseReference;
    Intent mIntent;
    String mLevelName, mCourseName;

    ArrayList<VideoModel> mModel = new ArrayList<>();

    VideoAdapter mVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        InstViews();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Courses").child(mItemId).child("Folders").child(mLevelName).child("Vedios");
   GetVedios();
    }

    //----------------------------------------------------------------------------------------------
    private void InstViews() {
        mIntent = getIntent();
        mLevelName = mIntent.getStringExtra("Name");

        mCourseName = mItemId;

        mRecyclerView = findViewById(R.id.Rec);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);

    }

    //----------------------------------------------------------------------------------------------
    private void GetVedios() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mModel.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    videoModel = postSnapshot.getValue(VideoModel.class);
                    mModel.add(videoModel);
                }
                mVideoAdapter = new VideoAdapter(mModel, Video.this);
                mRecyclerView.setAdapter(mVideoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
