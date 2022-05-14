package com.migia.simpleLogin.controller;

public class Body{
    private String username;
    private String password;

    public Body(String username, String password) {
        this.username = username;
        this.password = password;
    }

public Body(){

}

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
