package com.example.worldacademy.Fragments.Models;

public class HomeVerticalAdapter {
 String mName , mKey , mLevels , mDescription;
 String mImage , mVedio;


    public HomeVerticalAdapter() {
    }

    public HomeVerticalAdapter(String mName, String mKey, String mLevels, String mDescription, String mImage, String mVedio) {
        this.mName = mName;
        this.mKey = mKey;
        this.mLevels = mLevels;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mVedio = mVedio;
    }

    public String getmName() {
        return mName;
    }

    public String getmKey() {
        return mKey;
    }

    public String getmLevels() {
        return mLevels;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmVedio() {
        return mVedio;
    }
}
