package com.example.worldacademy.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.worldacademy.Activities.PaymentMethodActivity;
import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements View.OnClickListener {
    View view;
    RelativeLayout  mLanguage, mLegalInformation, mAskedQuestions,  mPrivacyPolicy, mShiipingAddress;
    TextView mNotificaticationNumber, mLogOut;
    FirebaseAuth Auth = FirebaseAuth.getInstance();

    DatabaseReference mDatabaseReference2;

    SessionMangment mangment;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_more, container, false);
        mangment = new SessionMangment(getActivity());
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference("Notification");
        InstView();
        return view;
    }

    private void InstView() {
        mLogOut = view.findViewById(R.id.LogOut);
        mLogOut.setOnClickListener(this);


//        mLanguage = view.findViewById(R.id.mLanguageRelative);

        mLegalInformation = view.findViewById(R.id.mLegalInformationRelative);
        mAskedQuestions = view.findViewById(R.id.mAskedQuestionsRelative);
        mPrivacyPolicy = view.findViewById(R.id.mPrivacyPolicyRelative);
//        mShiipingAddress = view.findViewById(R.id.mshippingRelative);



        mAskedQuestions.setOnClickListener(this);

        mPrivacyPolicy.setOnClickListener(this);
//        mShiipingAddress.setOnClickListener(this);


        mLegalInformation.setOnClickListener(this);

//        mLanguage.setOnClickListener(this);

        mNotificaticationNumber = view.findViewById(R.id.number_navegation);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            case R.id.mLanguageRelative:
//                ChangeLanguageFragment mChangeLanguageFragment = new ChangeLanguageFragment();
//                mChangeLanguageFragment.show(getActivity().getSupportFragmentManager(), "");
//                break;

//            case R.id.mshippingRelative:


//                break;
            case R.id.mAskedQuestionsRelative:
                String url = "https://api.whatsapp.com/send?phone=" + "966 59 261 0089";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                break;
            case R.id.mPrivacyPolicyRelative:
                Privacy_Policy privacy_policy = new Privacy_Policy();
                privacy_policy.show(getActivity().getSupportFragmentManager(), "");

                break;

            case R.id.mLegalInformationRelative:
                Legal_Information Legal_Information = new Legal_Information();
                Legal_Information.show(getActivity().getSupportFragmentManager(), "");
                break;

            case R.id.LogOut:
                mangment.logoutUser();
                Auth.signOut();
                break;



        }
    }




}
