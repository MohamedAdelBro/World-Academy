package com.example.worldacademy.Fragments.Auth;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.worldacademy.Activities.MainActivity;
import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Models.NotificationModel;
import com.example.worldacademy.Fragments.Models.UsersModel;
import com.example.worldacademy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {


    View view;
    TextInputEditText mE_Mail, mPassword, mName, mAge, mPhone, mNationality, mFacebookLink, mWhatsAppNumber;
    FirebaseAuth mAuth;
    RelativeLayout signUprelative;
    SessionMangment mSession;
    DatabaseReference mUserDataRef, mDatabaseReference;
    static String UserId;

    NotificationModel model;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mAuth = FirebaseAuth.getInstance();
        mUserDataRef = FirebaseDatabase.getInstance().getReference("Users Data");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Notification");

        initViews();
        return view;

    }

    private boolean Validate() {

        if (mE_Mail.getText().toString().isEmpty()) {

            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your E-Mail", Snackbar.LENGTH_LONG).show();

            return false;
        } else if (mName.getText().toString().isEmpty()) {

            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your Name", Snackbar.LENGTH_LONG).show();

            return false;
        } else if (mWhatsAppNumber.getText().toString().isEmpty()) {

            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your Name", Snackbar.LENGTH_LONG).show();

            return false;
        } else if (mNationality.getText().toString().isEmpty()) {

            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your Name", Snackbar.LENGTH_LONG).show();

            return false;
        } else if (mFacebookLink.getText().toString().isEmpty()) {

            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your Name", Snackbar.LENGTH_LONG).show();

            return false;
        } else if (mPassword.getText().toString().isEmpty()) {

            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your Password", Snackbar.LENGTH_LONG).show();

            return false;
        }
        else if (mPhone.getText().toString().isEmpty()) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter Your Phone", Snackbar.LENGTH_LONG).show();
            return false;
        }
        else if (mAge.getText().toString().isEmpty()) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter Your Age", Snackbar.LENGTH_LONG).show();
            return false;
        }
        else if (mPassword.getText().toString().length() < 8) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter 8 elements Or More ", Snackbar.LENGTH_LONG).show();
            return false;
        }
        else
            return true;

    }

    private void initViews() {
        mAge = view.findViewById(R.id.mUserAge);
        mPhone = view.findViewById(R.id.mPhoneNumber);
        mE_Mail = view.findViewById(R.id.muserMailTxt);
        mName = view.findViewById(R.id.muserNameTxt);
        mPassword = view.findViewById(R.id.muserPassword);
        mFacebookLink = view.findViewById(R.id.mFaceAccount);
        mNationality = view.findViewById(R.id.mNationality);
        mWhatsAppNumber = view.findViewById(R.id.mWhatsNumber);

        signUprelative = view.findViewById(R.id.msignUpRelative);
        signUprelative.setOnClickListener(this);
        mSession = new SessionMangment(getActivity());

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.msignUpRelative:
                if (Validate()) {
                    Rigester();
                }
                break;

        }

    }


    public void Rigester() {
        mAuth.createUserWithEmailAndPassword(mE_Mail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),

                            "Sign Succeeded", Snackbar.LENGTH_LONG).show();
                    Intent mIntent = new Intent(getActivity(), MainActivity.class);
                    final String aUserId = FirebaseAuth.getInstance().getUid();
                    UserId = aUserId;
                    mIntent.putExtra("User Id", aUserId);
                    mIntent.putExtra("KindOfEdit", 1);
                    startActivity(mIntent);
                    mSession.createLoginSession(true, aUserId, mName.getText().toString(), mE_Mail.getText().toString());
                    AddNote();
                    SendNotification();
                    getActivity().finish();
                } else {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            task.getException().getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                    Log.e("Falierrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", task.getException().getMessage());

                }

            }
        });

    }

    private void SendNotification() {

        model = new NotificationModel("Welcome to Our App", mName.getText().toString());
        mDatabaseReference.child(UserId).child(mName.getText().toString()).setValue(model);
    }

    void AddNote() {

        UsersModel model = new UsersModel(mName.getText().toString(), mE_Mail.getText().toString(), mPhone.getText().toString(), mAge.getText().toString(), mNationality.getText().toString(), mWhatsAppNumber.getText().toString(), mFacebookLink.getText().toString());
        mUserDataRef.child(UserId).setValue(model);

    }

}

