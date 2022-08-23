package com.zzuli.exer;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 获取当前运行时类的属性结构
 * @auther pony
 * @create 2022-03-22 19:31
 */
public class FileldTest {
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("com.zzuli.exer.Person");

//        获取属性结构
        Field[] fields = clazz.getFields();
        for (Field f: fields) {
            System.out.println(f);
        }
    }
}
