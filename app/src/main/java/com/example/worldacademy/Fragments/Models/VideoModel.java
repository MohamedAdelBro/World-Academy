package com.example.worldacademy.Fragments.Models;

public class VideoModel {
    String mDescribtion, mLink;

    public VideoModel(String mDescribtion, String mLink) {
        this.mDescribtion = mDescribtion;
        this.mLink = mLink;
    }

    public VideoModel() {

    }

    public String getmDescribtion() {
        return mDescribtion;
    }

    public String getmLink() {
        return mLink;
    }
}
