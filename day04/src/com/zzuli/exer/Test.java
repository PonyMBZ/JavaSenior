package com.zzuli.exer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @auther pony
 * @create 2022-03-05 17:54
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入第一个日期：");
        String day1 = scanner.next();
        System.out.println("请输入第二个日期：");
        String day2 = scanner.next();
//        System.out.println(day1);
//        System.out.println(day2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse1 = simpleDateFormat.parse(day1);
        Date parse2 = simpleDateFormat.parse(day2);
        System.out.println(((parse2.getTime() - parse1.getTime()) / (1000 * 60 * 60 * 24) + 1));

   }
}
