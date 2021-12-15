package com.shopping.dao;

import com.shopping.po.User;
import com.shopping.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User Login(User user) {
        String sql = "select username,password from user where username = ? and password = ?";
        ResultSet rs = DbUtil.execQuery(sql, new String[] { user.getUsername(), user.getPassword() });
        try {
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll();
        }
        return user;
    }
public int getUserId(String username){
        String sql = "select id from user where username = ?";
        ResultSet rs = DbUtil.execQuery(sql,new String[]{username});
        try {
            if (rs.next()){
               return rs.getInt("id");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
}

    public int Register(User user) {
        String sql = "insert into user values(null,?,?)";
        int n = DbUtil.execUpdate(sql, new String[] { user.getUsername(), user.getPassword() });
        // DbUtil.closeAll();
        return n;
    }
}
