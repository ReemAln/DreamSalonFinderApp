package com.example.dreamsalonfinderapp;

public class UserHelperClass {

    String name,username,email,phoneNo,password, confirmPassword;

    public UserHelperClass(){}
    public UserHelperClass(String name, String username, String email, String phoneNo, String password, String confirmPassword) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phoneNo;
    }

    public void setPhone(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return password;
    }

    public void setConfirmPassword(String password) {
        this.password = password;
    }
}