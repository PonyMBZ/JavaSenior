package com.zzuli.java;

import org.junit.Test;

import java.util.Date;


/**
 * JDK 8 之前日期和时间的API测试
 * @auther pony
 * @create 2022-03-04 14:42
 */
public class DateTimeTest {

    @Test
    public void test2(){
        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date.getTime());
        Date date1 = new Date(1646377025811L);
        System.out.println(date1);
        java.sql.Date date2 = new java.sql.Date(1646377025811L);
        System.out.println(date2);

        Date date3 = new java.sql.Date(1646377025811L);
        java.sql.Date date4 = (java.sql.Date) date3;

        Date date5 = new Date();
        java.sql.Date date6 = new java.sql.Date(new Date().getTime());

        System.out.println(date6);
    }
    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        System.out.println(time);

    }
}
