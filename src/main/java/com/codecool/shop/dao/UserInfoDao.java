package com.codecool.shop.dao;

import com.codecool.shop.model.User;
import com.codecool.shop.model.UserInfo;

import java.util.List;

public interface UserInfoDao {

    void add(UserInfo userInfo);

    void update(UserInfo userInfo);

    UserInfo find(int id);

    List<UserInfo> getAll();
}
