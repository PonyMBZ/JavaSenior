package com.zzuli.java;

import org.junit.Test;

/**
 * @auther pony
 * @create 2022-03-05 16:34
 */
public class testStringBuffer {

    @Test
    public void test1(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);

        System.out.println(sb.length());//4
        System.out.println(sb);//"null"
        StringBuffer sb1 = new StringBuffer(str);//抛异常
        System.out.println(sb1);
    }

}
