package com.zzuli.demo2;

/**
 * @auther pony
 * @create 2022-04-24 18:12
 */
public class MainClass {
    public static void main(String[] args) {
        Generic<String> stringGeneric = new Generic<>("abc");
        String key1 = stringGeneric.getKey();
        System.out.println("key1:" + key1);
    }
}
