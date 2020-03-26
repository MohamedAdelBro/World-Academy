package com.example.worldacademy.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Adapters.Adapter_Horizontal;
import com.example.worldacademy.Fragments.Adapters.Adapter_Vertical;
import com.example.worldacademy.Fragments.Adapters.SmoothLayoutManager;
import com.example.worldacademy.Fragments.Models.HomeVerticalAdapter;
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
public class homeFragment extends Fragment implements View.OnClickListener {
    View view;
    RelativeLayout mNotification;
    RecyclerView mRecVert;

    TextView mNumberOfNotification;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseRef;

    ArrayList<String> mImages = new ArrayList<>();
    ArrayList<HomeVerticalAdapter> mItemVert = new ArrayList<>();
    static int numberOfNMoti = 0;
    DatabaseReference mDatabaseReference2;
    SessionMangment mangment;

    //----------------------------------------------------------------------------------------------
    final int speedScroll = 3500;
    final Handler handler = new Handler();
    RecyclerView mReoHori;
    Adapter_Horizontal mAdapter_horizontal;
    notivegationFragment mNotivegationFragment;

    //----------------------------------------------------------------------------------------------
    ImageView mFaceBook, mInstgram, mSnapShat;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mangment = new SessionMangment(getActivity());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("seeMore");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Courses");
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference("Notification").child(mangment.getUserDetails().get(mangment.KEY_ID));

        instView();

        LoadHori();
        getNumberofNotification();
        LoadVert();
        return view;
    }
    //----------------------------------------------------------------------------------------------

    public void onStart() {
        super.onStart();

        getNumberofNotification();
        LoadVert();

    }
    //----------------------------------------------------------------------------------------------

    private void instView() {


        mReoHori = view.findViewById(R.id.RecHori);

//--------------------------------------------------------------------------------------------------

        mFaceBook = view.findViewById(R.id.Facebook);
        mInstgram = view.findViewById(R.id.Instgram);
        mSnapShat = view.findViewById(R.id.SnapShat);

        mSnapShat.setOnClickListener(this);
        mFaceBook.setOnClickListener(this);
        mInstgram.setOnClickListener(this);

//--------------------------------------------------------------------------------------------------

        mNotivegationFragment = new notivegationFragment();
        mNumberOfNotification = view.findViewById(R.id.number_navegation);

//--------------------------------------------------------------------------------------------------

        mNotification = view.findViewById(R.id.notification);

        mNotification.setOnClickListener(this);

//--------------------------------------------------------------------------------------------------
        mRecVert = view.findViewById(R.id.RecVer);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecVert.setLayoutManager(layoutManager);


    }

    //----------------------------------------------------------------------------------------------
    void LoadHori() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mImages.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    String imageUploadInfo = postSnapshot.getValue(String.class);

                    mImages.add(imageUploadInfo);
                }

                Adapter_Horizontal adapter_horizontal = new Adapter_Horizontal(mImages, getActivity());

                mReoHori.setAdapter(adapter_horizontal);
                scroll();
                ScrollSmothey(getActivity());

                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }

    //----------------------------------------------------------------------------------------------
    void LoadVert() {
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mItemVert.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    HomeVerticalAdapter imageUploadInfo = postSnapshot.getValue(HomeVerticalAdapter.class);

                    mItemVert.add(imageUploadInfo);
                }

                Adapter_Vertical adapter_horizontal = new Adapter_Vertical(mItemVert, getActivity());

                mRecVert.setAdapter(adapter_horizontal);


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });

    }

    //----------------------------------------------------------------------------------------------

    private void ScrollSmothey(Context iC) {
        mAdapter_horizontal = new Adapter_Horizontal(mImages, getActivity());

        mReoHori.setLayoutManager(new SmoothLayoutManager(iC, LinearLayoutManager.HORIZONTAL, false).setSpeedOfSmooth(SmoothLayoutManager.X_200));
        mReoHori.setAdapter(mAdapter_horizontal);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mReoHori);
    }

    //----------------------------------------------------------------------------------------------
    void scroll() {
        final Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;

            @Override
            public void run() {
                if (count < mAdapter_horizontal.getItemCount()) {
                    if (count == mAdapter_horizontal.getItemCount() - 1) {
                        flag = false;
                    } else if (count == 0) {
                        flag = true;
                    }
                    if (flag) count++;
                    else count--;

                    mReoHori.smoothScrollToPosition(count);
                    handler.postDelayed(this, speedScroll);
                }
            }
        };

        handler.postDelayed(runnable, speedScroll);
    }

    //----------------------------------------------------------------------------------------------
    void getNumberofNotification() {
        mDatabaseReference2.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                numberOfNMoti = 0;

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {


                    numberOfNMoti++;

                }
                mNumberOfNotification.setText(String.valueOf(numberOfNMoti));


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.notification:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.replace, new notivegationFragment()).commit();
                break;
            case R.id.Facebook:
                String YourPageURL = "https://web.facebook.com/waheed.shaaban";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                startActivity(browserIntent);
                break;

            case R.id.Instgram:
                String YourPageURL2 = "https://www.instagram.com/";
                Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL2));
                startActivity(browserIntent2);
                break;

            case R.id.SnapShat:
                String YourPageURL3 = "https://www.snapchat.com/";
                Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL3));
                startActivity(browserIntent3);
                break;
        }
    }
}

