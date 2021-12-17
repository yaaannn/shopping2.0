package com.shopping.dao;

import com.shopping.po.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void getUserId() {
        UserDao userDao = new UserDao();
        String username = "admin1";
        int a = userDao.getUserId(username);
        System.out.println(a);
    }

    @Test
    void login() {
        UserDao userDao = new UserDao();
        User user = userDao.Login("admin","123");
        System.out.println(user.getUsername());
    }
}