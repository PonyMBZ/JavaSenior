package com.zzuli.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @auther pony
 * @create 2022-03-05 17:05
 */
public class DateTimeTest {
    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

//        格式化
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        System.out.println(format);

//        解析
        String str = "22-3-4 下午5:20";
        Date date1 = simpleDateFormat.parse(str);
        System.out.println(date1);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date2 = new Date();
        String format1 = simpleDateFormat1.format(date2);
        System.out.println(format1);
    }
    /**
     *
     */
    @Test
    public void test2() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入第一个日期：");
        String day1 = scanner.next();
        System.out.println("请输入第二个日期：");
        String day2 = scanner.next();
        System.out.println(day1);
        System.out.println(day2);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse1 = simpleDateFormat.parse(day1);
//        Date parse2 = simpleDateFormat.parse(day2);
//        System.out.println(((parse2.getTime() - parse1.getTime()) / (1000 * 60 * 60 * 24) + 1));


    }
    @Test
    public void test3(){
        //实例化
        Calendar instance = Calendar.getInstance();
//        System.out.println(instance.getClass());
        int day = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
    }
}
