package com.shopping.util;

import java.sql.*;

public class DbUtil {
    private static final String url = "jdbc:mysql://localhost:3306/shopping";
    private static final String user = "root";
    private static final String password = "heyan5201314";
    private static final String driverClass = "com.mysql.cj.jdbc.Driver";

    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    public static Connection getConn() throws Exception {
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    // 执行查询的方法
    public static ResultSet execQuery(String sql, String[] args) {
        try {
            conn = DbUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setString(i + 1, args[i]);
            }
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    // 执行更新的方法
    public static int execUpdate(String sql, String[] args) {
        try {
            conn = DbUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setString(i + 1, args[i]);
            }
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    // 执行关闭的方法
    public static void closeAll() {
        try {
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
