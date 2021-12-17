package com.shopping.po;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cart {

    private HashMap<Good, Integer> goods;
    private double totalPrice;

    public Cart() {
        goods = new HashMap<>();
        totalPrice = 0.0;
    }

    public HashMap<Good, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<Good, Integer> goods) {
        this.goods = goods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean addGoodsInCart(Good good, int number) {
        if (goods.containsKey(good)) {
            goods.put(good, goods.get(good) + number);
        } else {
            goods.put(good, number);
        }
        calTotalPrice(); // 重新计算购物车的总金额
        return true;
    }

    // 删除商品的方法
    public boolean removeGoodsFromCart(Good good) {
        goods.remove(good);
        calTotalPrice(); // 重新计算购物车的总金额
        return true;
    }

    // 统计购物车的总金额
    public double calTotalPrice() {
        double sum = 0.0;
        Set<Good> keys = goods.keySet(); // 获得键的集合
        Iterator<Good> it = keys.iterator(); // 获得迭代器对象
        while (it.hasNext()) {
            Good i = it.next();
            sum += i.getPrice() * goods.get(i);
        }
        this.setTotalPrice(sum); // 设置购物车的总金额
        return this.getTotalPrice();
    }

    public static void main(String[] args) {

        // 先创建两个商品对象
        Good i1 = new Good(1, "沃特篮球鞋", "温州", 200, 500, "001.jpg");
        Good i2 = new Good(2, "李宁运动鞋", "广州", 300, 500, "002.jpg");
        Good i3 = new Good(1, "沃特篮球鞋", "温州", 200, 500, "001.jpg");

        Cart c = new Cart();
        c.addGoodsInCart(i1, 1);
        c.addGoodsInCart(i2, 2);
        // 再次购买沃特篮球鞋，购买3双
        c.addGoodsInCart(i3, 3);

        c.removeGoodsFromCart(i3);

        // 变量购物商品的集合
        Set<Map.Entry<Good, Integer>> items = c.getGoods().entrySet();
        for (Map.Entry<Good, Integer> obj : items) {
            System.out.println(obj);
        }

        System.out.println("购物车的总金额：" + c.getTotalPrice());

    }

}
