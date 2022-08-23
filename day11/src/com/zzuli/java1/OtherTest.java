package com.zzuli.java1;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取构造器
 * @auther pony
 * @create 2022-03-24 16:21
 */
public class OtherTest {
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("com.zzuli.exer.Person");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c:constructors) {
            System.out.println(c);
        }

        System.out.println();

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();

        for (Constructor c: declaredConstructors) {
            System.out.println(c);
        }
    }

//    1.获取运行时类的父类
    @Test
    public void test2() throws ClassNotFoundException {
        Class clazz = Class.forName("com.zzuli.exer.Person");
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);

//        2.带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);

//        3.带泛型的父类的泛型
        Type genericSuperclass1 = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass1;
//        获取泛型类型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
//        方式一：
//        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class) actualTypeArguments[0]).getName());
    }

    //获取运行时类实现的接口
    @Test
    public void test3() throws ClassNotFoundException {
        Class clazz = Class.forName("com.zzuli.exer.Person");

        Class[] interfaces = clazz.getInterfaces();
        for (Class c: interfaces) {
            System.out.println(c);
        }

        System.out.println();

        //获取运行时类父类的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class c: interfaces1) {
            System.out.println(c);
        }
    }

    //获取运行时类所在的包
    @Test
    public void test4() throws ClassNotFoundException {
        Class clazz = Class.forName("com.zzuli.exer.Person");

        Package pack = clazz.getPackage();
        System.out.println(pack);

        //获取运行时类声明的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annos: annotations) {
            System.out.println(annos);
        }
    }

}
