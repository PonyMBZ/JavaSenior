package com.zzuli.java1;

/**
 * 单例模式 ——饿汉式
 * @auther pony
 * @create 2022-03-01 21:20
 */
public class BankTest2 {

}

class Order2{
    private Order2(){
    }

    public static Order2 order2 = new Order2();

    public static Order2 getOrder(){
        return order2;
    }
}