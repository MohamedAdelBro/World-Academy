package com.example.worldacademy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SessionMangment mSessionMangment = new SessionMangment(Splash.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSessionMangment.isLoggedIn()){
                    startActivity(new Intent(Splash.this,MainActivity.class));
                    finish();
                }
                else
                    startActivity(new Intent(Splash.this,AuthActivity.class));
                finish();

            }
        }, 1000);

    }
    }

