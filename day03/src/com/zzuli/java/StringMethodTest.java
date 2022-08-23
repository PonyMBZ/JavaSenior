package com.zzuli.java;

import org.junit.Test;

import java.util.Locale;

/**
 * @auther pony
 * @create 2022-03-03 16:26
 */
public class StringMethodTest {
    @Test
    public void test4(){
        String str1 = "郑州轻工业大学郑州";
        String str2 = str1.replace('郑', '广');
        System.out.println(str2);

        String str3 = str1.replace("郑州", "北京");
        System.out.println(str3);


    }
    @Test
    public void test3(){
        String str1 = "helloworld";
        System.out.println(str1.endsWith("ld"));

        System.out.println(str1.startsWith("hell"));

        System.out.println(str1.startsWith("llo", 2));

        String str2 = "wo";
        System.out.println(str1.contains(str2));

        System.out.println(str1.indexOf("lo"));
    }
    @Test
    public void test2() {

        String s1 = "HelloWorld";
        String s2 = "helloworld";

        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));

        String s3 = "abc";
        String s4 = s3.concat("def");
        System.out.println(s4);

        String s5 = "abc";
        String s6 = new String("abe");
        System.out.println(s5.compareTo(s6));

        String s7 = "pony自学Java";
        String s8 = s7.substring(4);
        System.out.println(s7);
        System.out.println(s8);

        String s9 = s7.substring(0, 4);  //[0, 4)
        System.out.println(s9);
    }

    @Test
    public void test1(){
        String s1 = "HelloWorld";
        System.out.println(s1.length());//10
        System.out.println(s1.charAt(0));//h
        System.out.println(s1.isEmpty());//false

        String s2 = s1.toLowerCase();
        System.out.println(s1);//HelloWorld 数组不可变
        System.out.println(s2);//helloworld

        String s3 = s1.toUpperCase();
        System.out.println(s1);
        System.out.println(s3);

        String s4 = "    h e llo   ";
        String s5 = s4.trim();
        System.out.println("------" + s4 + "------");
        System.out.println("------" + s5 + "------");

    }
}
