package com.zzuli.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther pony
 * @create 2022-03-13 16:58
 */
public class GenericTest1 {
    //测试泛型方法
    @Test
    public void test4(){

        Order<String> stringOrder = new Order<String>();
        Integer[] arr=new Integer[]{1,2,3,4};
        List<Integer> integers = stringOrder.copyFromArrayToList(arr);
        System.out.println(integers);
    }

    @Test
    public void test3(){

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
//        泛型类型不一样不能赋值
//        list1 = list2;
    }
    @Test
    public void test2(){
        Suborder suborder = new Suborder();
        suborder.setOrderT(123);
        System.out.println(suborder.getOrderT());
        Suborder1<String> stringSuborder1 = new Suborder1<String>();
        stringSuborder1.setOrderT("Tom");
        System.out.println(stringSuborder1.getOrderT());
    }
    @Test
    public void test1(){

        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("Tom");

        Order<String> stringOrder = new Order<>();
        stringOrder.setOrderT("Tom:hello");
        System.out.println(stringOrder.getOrderT());
    }

}
