package com.zzuli.java1;

/**
 * 解决线程不安全 对单例模式 —— 懒汉式 的改造
 * @auther pony
 * @create 2022-03-01 21:09
 */
public class BankTest {
}

class Order1{

//    私有化构造器
    private Order1(){

    }

    public static  Order1 order = null;

    public static Order1 getOrder() {
//        方式一：效率稍低
//        synchronized (Order1.class){
//            if(order == null){
//                order = new Order1();
//            }
//            return order;
//        }
//        方式二：效率稍高
        if (order == null) {
            synchronized (Order1.class) {
                if (order == null) {
                    order = new Order1();
                }

            }
        }
        return order;
}}
