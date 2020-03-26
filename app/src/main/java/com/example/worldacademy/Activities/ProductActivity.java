package com.example.worldacademy.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Adapters.fragmentPagerAdapter;
import com.example.worldacademy.Fragments.DetailsFragment;
import com.example.worldacademy.Fragments.Models.HomeVerticalAdapter;
import com.example.worldacademy.Fragments.Models.VideoModel;
import com.example.worldacademy.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mBcakToHome;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    fragmentPagerAdapter fragmentPagerAdapter;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReference2;
    TextView mPrice, mName;
    public String ProductId;
    Intent mIntent;
    static int numberOfNMoti = 0;
    SessionMangment mangment;
    VideoView videoModel;
    Uri mUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mangment = new SessionMangment(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Courses");
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference("Notification").child(mangment.getUserDetails().get(mangment.KEY_ID));
        getNumberofNotification();
        instView();
    }

    private void instView() {



        mBcakToHome = findViewById(R.id.backToHome);


        mBcakToHome.setOnClickListener(this);

        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.viewpager);
        fragmentPagerAdapter = new fragmentPagerAdapter(ProductActivity.this.getSupportFragmentManager());
        fragmentPagerAdapter.showFragments(new DetailsFragment(), "details");

        mViewPager.setAdapter(fragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        videoModel = findViewById(R.id.Video);
        mPrice = findViewById(R.id.Price);
        mName = findViewById(R.id.Name);
        mIntent = getIntent();
        ProductId = mIntent.getStringExtra("ItemKey");

        mDatabaseReference.child(ProductId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                HomeVerticalAdapter homeVerticalAdapter = dataSnapshot.getValue(HomeVerticalAdapter.class);
                mName.setText(homeVerticalAdapter.getmName());


                MediaController mediaController = new MediaController(ProductActivity.this);
                mediaController.setAnchorView(videoModel);

                mUri = Uri.parse(homeVerticalAdapter.getmVedio());

                videoModel.setMediaController(mediaController);
                videoModel.setVideoURI(mUri);


                videoModel.invalidate();

                videoModel.start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.backToHome:
                Intent mIntent = new Intent(this , MainActivity.class);
                startActivity(mIntent);
                break;



        }

    }

    void getNumberofNotification() {
        mDatabaseReference2.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                numberOfNMoti = 0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {


                    numberOfNMoti++;

                }


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }

}
