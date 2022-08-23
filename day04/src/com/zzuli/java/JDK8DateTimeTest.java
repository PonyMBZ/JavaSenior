package com.zzuli.java;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @auther pony
 * @create 2022-03-05 20:02
 */
public class JDK8DateTimeTest {
    @Test
    public void test1(){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        String str = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(str);

        //字符串 ---> 日期
        TemporalAccessor parse = formatter.parse("2022-03-06T10:30:46.004");
        System.out.println(parse);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String str2 = formatter1.format(LocalDateTime.now());
        System.out.println(str2);
        //解析
        TemporalAccessor parse1 = formatter1.parse("2022-03-06 10:41:44");

        System.out.println(parse1);

    }

    @Test
    public void testDate(){
        //now():获取当前时间日期
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
        //of():设置指定时间没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 43);
        System.out.println(localDateTime1);

        //getXxx():获得相关属性
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //不可变性
        //whitXxx():设置相关属性
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);
        System.out.println(localDate1);

        //不可变性
        //plusXxx():
        LocalDateTime localDateTime2 = localDateTime.plusMinutes(3);

//        minusXXX
    }
}
