package com.zzuli.java;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class StringTest1 {

    /*
    * String 与 byte 之间的转换
    * String --> byte[]:调用String的getBytes()
    *
    * */
    @Test
    public void test2() throws UnsupportedEncodingException {
        String str1 = "abc123";
        byte[] bytes = str1.getBytes();
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(bytes));
        String str = new String(bytes, "utf-8");
        System.out.println(str);
    }
    /**
     * String 与 char[]之间的转化
     * @auther pony
     * @create 2022-03-03 19:50
     */
//  String ---> char[]
    @Test
    public void test1(){
        String str1 = "abc123";

        char[] chars = str1.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }

        char[] arr = new char[]{'h','e','l','l','o'};
        String str2 = new String(arr);
        System.out.println(str2);
    }
}
