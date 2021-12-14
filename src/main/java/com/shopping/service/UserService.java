package com.shopping.service;

import com.shopping.dao.UserDao;
import com.shopping.po.User;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User userLog(String username, String password) {
        return userDao.Login(new User(username, password));
    }

    public int userReg(String username, String password) {
        return userDao.Register(new User(username, password));
    }

}
