package com.example.worldacademy.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.worldacademy.Activities.Levels;
import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Models.CartModel;
import com.example.worldacademy.Fragments.Models.HomeVerticalAdapter;
import com.example.worldacademy.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements View.OnClickListener {


    View view;
    RelativeLayout addtocartRelative, sharethisRelative;
    TextView mDescribtionFiled;
    static String mItemId;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReferenceForCart;
    DatabaseReference mDatabaseReferenceToHistory;
    SessionMangment mangment;
    Intent mIntent;
//    CartModel homeVerticalAdapter;
    HomeVerticalAdapter mHomeVerticalAdapter;

    static String  mDescribtion;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details, container, false);

        mangment = new SessionMangment(getActivity());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Courses");
        mDatabaseReferenceForCart = FirebaseDatabase.getInstance().getReference("Cart").child(mangment.getUserDetails().get(mangment.KEY_ID));
        mDatabaseReferenceToHistory = FirebaseDatabase.getInstance().getReference("History Orders").child(mangment.getUserDetails().get(mangment.KEY_ID));
        initViews();
        return view;

    }

    private void initViews() {
        mIntent = getActivity().getIntent();

        mDescribtionFiled = view.findViewById(R.id.describtion);
        mItemId = mIntent.getStringExtra("ItemKey");

        mDescribtionFiled.setText(mDescribtion);

//        categorytv = view.findViewById(R.id.mcategorytv);
//        brandtv = view.findViewById(R.id.mbrandtv);
//        conditiontv = view.findViewById(R.id.mconditiontv);
//        skutv = view.findViewById(R.id.mskutv);
//        materialtv = view.findViewById(R.id.mmaterialtv);
//        weighttv = view.findViewById(R.id.mweighttv);
//        categorytv.setText(mIntent.getStringExtra("mName"));

        addtocartRelative = view.findViewById(R.id.maddtocartDetailsRelative);
        sharethisRelative = view.findViewById(R.id.msharethisDetailsRelative);

        addtocartRelative.setOnClickListener(this);
        sharethisRelative.setOnClickListener(this);
//        skutv.setOnClickListener(this);
//        materialtv.setOnClickListener(this);
//        categorytv.setOnClickListener(this);
//        conditiontv.setOnClickListener(this);
//        brandtv.setOnClickListener(this);
//        weighttv.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {


            case R.id.msharethisDetailsRelative:
                String url = "https://api.whatsapp.com/send?phone=" + "966 59 261 0089";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                break;

            case R.id.maddtocartDetailsRelative:

                Intent mIntent = new Intent(getActivity(), Levels.class);
                mIntent.putExtra("ItemId",mItemId);
                startActivity(mIntent);
                getActivity().finish();

                break;
//            case R.id.mbrandtv:
//
//                break;
//            case R.id.mcategorytv:
//
//                break;
//            case R.id.mskutv:
//
//                break;
//            case R.id.mmaterialtv:
//
//                break;
//
//            case R.id.mweighttv:
//
//                break;
//
//            case R.id.mconditiontv:
//
//                break;

        }
    }

//    private void AddToCardChild() {
//        mDatabaseReference.child(mItemId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                homeVerticalAdapter = dataSnapshot.getValue(CartModel.class);
//
//                CartModel model = new CartModel(homeVerticalAdapter.getmPrice(), homeVerticalAdapter.getmName(), mItemId, 1, homeVerticalAdapter.getmImage());
//
//                mName = homeVerticalAdapter.getmName();
//
//
//                mDatabaseReferenceForCart.child(homeVerticalAdapter.getmKey()).setValue(model);
//
//                mDatabaseReferenceToHistory.child(homeVerticalAdapter.getmKey()).setValue(model);
//
//                Snackbar.make(getActivity().findViewById(android.R.id.content), "The Item Is Added To Card And Your History", Snackbar.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


    @Override
    public void onStart() {

        super.onStart();
        mDatabaseReference.child(mItemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mHomeVerticalAdapter = dataSnapshot.getValue(HomeVerticalAdapter.class);
                mDescribtionFiled.setText(mHomeVerticalAdapter.getmDescription());
                mDescribtionFiled.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}


