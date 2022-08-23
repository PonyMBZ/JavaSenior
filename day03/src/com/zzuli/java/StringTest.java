package com.zzuli.java;

import org.junit.Test;

/**
 * @auther pony
 * @create 2022-03-03 14:35
 */
public class StringTest {
    /*
    * 1.String 声明为finall的，不可被继承
    * 2.String实现了Serializable接口，表示字符串是支持序列化的
    *         实现了Comparable接口，表示String可以比较大小
    * 3.String在内部定义了final char[] value用于存储字符串数据
    * 4.String：代表不可变的字符序列。简称不可变性。
    * */
    @Test
    public void test1(){

        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);
        s1 = "hello";
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test2(){

        String s1 = "javaEE";
        String s2 = "javaEE";

        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);
        System.out.println(s3 == s1);
        System.out.println(s3 == s4);

        Person p1 = new Person("Tom", 12);
        Person p2 = new Person("Tom", 12);
        System.out.println(p1.name.equals(p2.name));
        System.out.println(p1 == p2);
        System.out.println(p1.name == p2.name);
    }

    @Test
    public void Test(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop"; //字面量；连接在常量池中创建
        String s5 = s1 + "hadoop";       //有变量，在堆空间开辟，相当于new
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
    }

}
