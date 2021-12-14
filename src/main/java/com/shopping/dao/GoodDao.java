package com.shopping.dao;

import com.shopping.po.Good;
import com.shopping.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDao {

    public List<Good> getAllGoods() {
        List<Good> goods = new ArrayList<>();
        String sql = "select * from good";
        ResultSet rs = DbUtil.execQuery(sql, new String[] {});
        try {
            while (rs.next()) {
                Good good = new Good(rs.getInt("id"), rs.getString("name"),
                        rs.getString("city"), rs.getInt("price"),
                        rs.getInt("number"), rs.getString("picture"));
                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll();
        }
        return goods;
    }

    public Good getGoodById(int id) {
        String sql = "select * from good where id = ?";
        ResultSet rs = DbUtil.execQuery(sql, new String[] { String.valueOf(id) });
        try {
            if (rs.next()) {
                Good good = new Good(rs.getInt("id"), rs.getString("name"),
                        rs.getString("city"), rs.getInt("price"),
                        rs.getInt("number"), rs.getString("picture"));
                return good;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbUtil.closeAll();
        }
    }

    public List<Good> getViewList(String list) {
        List<Good> goodList = new ArrayList<>();
        int count = 5;
        if (list != null && list.length() > 0) {
            String[] arr = list.split(",");

            if (arr.length >= 5) {
                for (int i = arr.length - 1; i >= arr.length - count; i--) {
                    goodList.add(getGoodById(Integer.parseInt(arr[i])));
                }
            } else {
                for (int i = arr.length - 1; i >= 0; i--) {
                    goodList.add(getGoodById(Integer.valueOf(arr[i])));

                }
            }
            return goodList;
        } else {
            return null;
        }
    }
}
