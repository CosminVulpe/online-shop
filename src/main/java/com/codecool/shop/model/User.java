package com.codecool.shop.model;

public class User {
    private String name;
    private String email;
    private String password;

    private int id;

    public User(String name
            , String email
            , String password) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.id = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
