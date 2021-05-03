package com.example.deelio.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class Deal implements Parcelable, Serializable {
    private String UserReference = ""; //to retain the reference user (publisher) who creates this deal
    private String DealId = ""; //a unique id to assign on every single deal
    private String DealImage = "";
    private String Title = "";
    private String DealURL="";
    private String BeforePrice= "";
    private String AfterPrice= "";
    private String StoreName= "";
    private String LikeCount= "";
    private String CommentCount= "";
    private String Details= "";

    public Deal(String dealImage, String title, String beforePrice, String afterPrice, String storeName, String likeCount, String commentCount, String details, String url) {
        DealImage = dealImage;
        Title = title;
        BeforePrice = beforePrice;
        AfterPrice = afterPrice;
        StoreName = storeName;
        LikeCount = likeCount;
        CommentCount = commentCount;
        Details = details;
        DealURL = url;
    }

    public Deal() {
    }

    protected Deal(Parcel in) {
        UserReference = in.readString();
        DealId = in.readString();
        DealImage = in.readString();
        Title = in.readString();
        DealURL= in.readString();
        BeforePrice = in.readString();
        AfterPrice = in.readString();
        StoreName = in.readString();
        LikeCount = in.readString();
        CommentCount = in.readString();
        Details = in.readString();
    }

    public static final Creator<Deal> CREATOR = new Creator<Deal>() {
        @Override
        public Deal createFromParcel(Parcel in) {
            return new Deal(in);
        }

        @Override
        public Deal[] newArray(int size) {
            return new Deal[size];
        }
    };

    public String getDealImage() { return DealImage; }
    public String getTitle() { return Title; }
    public String getBeforePrice() { return BeforePrice; }
    public String getAfterPrice() { return AfterPrice; }
    public String getStoreName() { return StoreName; }
    public String getLikeCount() { return LikeCount; }
    public String getCommentCount() { return CommentCount; }
    public String getDetails() { return Details; }

    public void setUserReference(String userReference) { UserReference = userReference;}
    public void setDealId(String dealId) { DealId = dealId; }
    public void setDealImage(String dealImage) { DealImage = dealImage; }
    public void setTitle(String title) { Title = title; }
    public void setDealURL(String dealURL) { DealURL = dealURL; }
    public void setBeforePrice(String beforePrice) { BeforePrice = beforePrice; }
    public void setAfterPrice(String afterPrice) { AfterPrice = afterPrice; }
    public void setStoreName(String storeName) { StoreName = storeName; }
    public void setLikeCount(String likeCount) { LikeCount = likeCount; }
    public void setCommentCount(String commentCount) { CommentCount = commentCount; }
    public void setDetails(String details) { Details = details;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(UserReference);
        parcel.writeString(DealId);
        parcel.writeString(DealImage);
        parcel.writeString(Title);
        parcel.writeString(DealURL);
        parcel.writeString(BeforePrice);
        parcel.writeString(AfterPrice);
        parcel.writeString(StoreName);
        parcel.writeString(LikeCount);
        parcel.writeString(CommentCount);
        parcel.writeString(Details);
    }
}
