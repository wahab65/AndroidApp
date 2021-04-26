package com.example.deelio.Model;

public class Deals {

    private String imageUrl;
    public Deals(){

    }
    public Deals(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
