package com.example.deelio.Model;

import android.widget.ImageView;
import android.widget.TextView;

public class Deal {
    private String DealImage = "";
    private String Title = "";
    private String BeforePrice= "";
    private String AfterPrice= "";
    private String StoreName= "";
    private String LikeCount= "";
    private String CommentCount= "";
    private String Details= "";

    public Deal(String dealImage, String title, String beforePrice, String afterPrice, String storeName, String likeCount, String commentCount, String details) {
        DealImage = dealImage;
        Title = title;
        BeforePrice = beforePrice;
        AfterPrice = afterPrice;
        StoreName = storeName;
        LikeCount = likeCount;
        CommentCount = commentCount;
        Details = details;
    }

    public String getDealImage() { return DealImage; }
    public String getTitle() { return Title; }
    public String getBeforePrice() { return BeforePrice; }
    public String getAfterPrice() { return AfterPrice; }
    public String getStoreName() { return StoreName; }
    public String getLikeCount() { return LikeCount; }
    public String getCommentCount() { return CommentCount; }
    public String getDetails() { return Details; }



}
