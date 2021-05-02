package com.example.deelio.Model;

public class User {

    private String Email="";
    private String username="";
    private int points=10;
    private String imageurl="";
    private String id="";
    private String userlevel="1";

    public User() {
    }

    public User(String Email, String id, String imageurl, int points,  String username) {
        this.Email = Email;
        this.id = id;
        this.imageurl = imageurl;
        this.points = points;
        this.username= username;
    }


    public String getEmail() { return Email ;}
    public void setEmail(String email) {
        this.Email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPoints() {
        return (String.valueOf(points));
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUserlevel() { return userlevel; }

    public void setUserlevel(String userlevel) { this.userlevel = userlevel; }
}

