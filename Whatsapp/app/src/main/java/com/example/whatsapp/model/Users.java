package com.example.whatsapp.model;

public class Users {

    private String id;
    private String username;
    private  String image;

    //default constructor
    public Users() {
    }

    //parameterised constructor


    public Users(String id, String username, String image) {
        this.id = id;
        this.username = username;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
