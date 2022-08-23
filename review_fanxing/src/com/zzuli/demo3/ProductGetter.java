package com.zzuli.demo3;

import java.util.ArrayList;
import java.util.Random;

/**
 * 抽奖泛型类
 * @auther pony
 * @create 2022-04-24 18:28
 */
public class ProductGetter<T> {
    Random random = new Random();//提供随机数
    //奖品
    private T product;

    //奖品池
    ArrayList<T> list = new ArrayList<>();

    //添加奖品
    public void addProduct(T t){
        list.add(t);
    }

    //抽奖
    public T getProduct(){
        product = list.get(random.nextInt(list.size()));
        return product;
    }
}
