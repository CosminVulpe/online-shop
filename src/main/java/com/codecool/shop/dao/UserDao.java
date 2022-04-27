package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {


    void add(User user);

    void update(User user);

    User get(int id);

    List<User> getAll();


}
