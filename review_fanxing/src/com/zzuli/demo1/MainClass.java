package com.zzuli.demo1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @auther pony
 * @create 2022-04-18 21:53
 */
public class MainClass {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("java");
        list.add(100);
        list.add(true);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
        System.out.println("***********************************************");
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("pony");
        list1.add("hanser");
        list1.add("蔡徐坤");
        Iterator iterator1 = list1.iterator();
        while (iterator1.hasNext()){
            Object next = iterator1.next();
            System.out.println(next);
        }
    }
}
