package com.shopping.service;

import com.shopping.dao.GoodDao;
import com.shopping.po.Good;

import java.util.List;

public class GoodService {
    private final GoodDao goodDao;

    public GoodService() {
        goodDao = new GoodDao();
    }

    public List<Good> getAllGoods() {
        return goodDao.getAllGoods();
    }

    public Good getGoodById(int id) {
        return goodDao.getGoodById(id);
    }

    public List<Good> getViewList(String list) {
        return goodDao.getViewList(list);
    }
}
