package com.example.worldacademy.Fragments.Models;

public class UsersModel {
    String mName,mEmail , mPhone, mBirthday , mNationality,mWhatsAppNumber,mFacebookAccount;

    public UsersModel(String mName, String mEmail, String mPhone, String mBirthday, String mNationality, String mWhatsAppNumber, String mFacebookAccount) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mBirthday = mBirthday;
        this.mNationality = mNationality;
        this.mWhatsAppNumber = mWhatsAppNumber;
        this.mFacebookAccount = mFacebookAccount;
    }

    public UsersModel() {

    }

    public String getmName() {
        return mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String mBirthday() {
        return mBirthday;
    }

    public String getmNationality() {
        return mNationality;
    }

    public String getmWhatsAppNumber() {
        return mWhatsAppNumber;
    }

    public String getmFacebookAccount() {
        return mFacebookAccount;
    }
}
