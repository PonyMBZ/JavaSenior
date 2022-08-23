package com.zzuli.exer1;

import com.zzuli.exer.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
//        getFields() : 获取当前运行时类及其父类中声明为publid的访问权限的属性
        Field[] fields = clazz.getFields();
        for (Field f: fields) {
            System.out.println(f);
        }
//        getDeclaredFields() : 获取当前运行时类中声明的所以属性。（不包含父类中声明的所以属性）
        Field[] declaredField = clazz.getDeclaredFields();
        for (Field f: declaredField) {
            System.out.println(f);
        }
    }
    //权限修饰符 数据类型 变量名
    @Test
    public void test2(){
        Class clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
//        权限修饰符
        for (Field f: declaredFields) {
            int modifiers = f.getModifiers();
            System.out.print(Modifier.toString(modifiers) + "\t");
//        数据类型
            Class type = f.getType();
            System.out.print(type + "\t");
//        变量名
            String name = f.getName();
            System.out.print(name);

            System.out.println();
        }

    }
}
