package com.example.worldacademy.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Adapters.NotifiCAtionAdapter;
import com.example.worldacademy.Fragments.Models.HomeVerticalAdapter;
import com.example.worldacademy.Fragments.Models.NotificationModel;
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
public class notivegationFragment extends Fragment {
    View view;
    RecyclerView mVertical;
    NotifiCAtionAdapter mAdapterForNotification;
    ArrayList<NotificationModel> mItem = new ArrayList<>();
    DatabaseReference mDatabaseReference;
    SessionMangment mangment;
    ImageView mX;
    TextView mnoItem;
    static int numberOfNMoti;

    public notivegationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notivegation, container, false);
        mangment = new SessionMangment(getActivity());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Notification").child(mangment.getUserDetails().get(mangment.KEY_ID));
        InstView();
        AdapterInst();
        LoadVert();
        return view;
    }

    private void InstView() {
        mnoItem = view.findViewById(R.id.NoItem);
        mX = view.findViewById(R.id.finish);
        mX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.replace, new homeFragment()).commit();
            }
        });

        AdapterInst();

    }

    private void AdapterInst() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mVertical = view.findViewById(R.id.Rec);
        mVertical.setLayoutManager(manager);

    }

    void LoadVert() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mItem.clear();


                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    NotificationModel imageUploadInfo = postSnapshot.getValue(NotificationModel.class);

                    mItem.add(imageUploadInfo);
                }
                numberOfNMoti = mItem.size();
                mAdapterForNotification = new NotifiCAtionAdapter(mItem, getActivity());

                mVertical.setAdapter(mAdapterForNotification);
                if (mItem.size() != 0) {
                    mnoItem.setVisibility(View.GONE);
                } else
                    mnoItem.setVisibility(View.VISIBLE);

                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });

    }

}
