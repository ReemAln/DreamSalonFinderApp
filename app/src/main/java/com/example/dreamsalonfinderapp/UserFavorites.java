package com.example.dreamsalonfinderapp;

public class UserFavorites {

    String name, lastMessage, lastMsgTime, phoneNo, address, services;
    int imageId;

    public UserFavorites(String name, String lastMessage, String lastMsgTime, String phoneNo, String address, int imageId, String services) {

        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMsgTime = lastMsgTime;
        this.phoneNo = phoneNo;
        this.address = address;
        this.imageId = imageId;
    }
}
