package com.shopping.dao;

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
}