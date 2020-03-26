package com.example.worldacademy.Fragments.Models;

public class LevelsModel {
    String mNumberOfLevels, mPrice, mImage , mName;

    public LevelsModel() {
    }

    public LevelsModel(String mNumberOfLevels, String mPrice, String mImage, String mName) {
        this.mNumberOfLevels = mNumberOfLevels;
        this.mPrice = mPrice;
        this.mImage = mImage;
        this.mName = mName;
    }

    public String getmNumberOfLevels() {
        return mNumberOfLevels;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmName() {
        return mName;
    }
}
