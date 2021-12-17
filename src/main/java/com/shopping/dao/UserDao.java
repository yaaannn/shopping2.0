package com.shopping.dao;

import com.shopping.po.User;
import com.shopping.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // 用户登录
    public User Login(String username, String password) {
        User user = new User();
        String sql = "select username,password from user where username = ? and password = ?";
        ResultSet rs = DbUtil.execQuery(sql, new String[]{username, password});
        try {
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll();
        }
        return null;
    }

    //根据用户名查找用户ID
    public int getUserId(String username) {
        String sql = "select id from user where username = ?";
        ResultSet rs = DbUtil.execQuery(sql, new String[]{username});
        try {
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //用户注册
    public int Register(User user) {
        String sql = "insert into user values(null,?,?)";
        int n = DbUtil.execUpdate(sql, new String[]{user.getUsername(), user.getPassword()});
        // DbUtil.closeAll();
        return n;
    }
}
