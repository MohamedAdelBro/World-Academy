package com.example.worldacademy.Fragments.Models;

public class NotificationModel {
    String mReport , mName;

    public NotificationModel(String mReport, String mName) {
        this.mReport = mReport;
        this.mName = mName;
    }

    public NotificationModel() {
    }

    public String getmReport() {
        return mReport;
    }

    public String getmName() {
        return mName;
    }
}
