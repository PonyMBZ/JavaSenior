package com.zzuli.java1;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *
 * 获取运行时类的方法结构
 * @auther pony
 * @create 2022-03-24 14:53
 */
public class MethodTest {
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("com.zzuli.exer.Person");

//        getMethods():获取当前运行时类及其所有父类中声明为 public 权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m: methods) {
            System.out.println(m);
        }
        System.out.println();
//        getDeclaredMethods():获取当前运行时类中声明的所有方法。（不包含父类中声明的方法）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m1: declaredMethods) {
            System.out.println(m1);
        }
    }

    @Test
    public void test2() throws ClassNotFoundException {

        Class clazz = Class.forName("com.zzuli.exer.Person");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m1: declaredMethods) {
            //1.注解
            Annotation[] annos = m1.getAnnotations();
            for (Annotation a : annos) {
                System.out.println(a);
            }
            //2.权限修饰符
            System.out.print(Modifier.toString(m1.getModifiers()) + "\t");

            //3.返回值类型
            System.out.print(m1.getReturnType().getName() + "\t");

            //4.方法名
            System.out.print(m1.getName());

            System.out.print("(");
            //5.形参列表
            Class[] parameterTypes = m1.getParameterTypes();
            if(!(parameterTypes == null && parameterTypes.length == 0)){

                for (int i = 0; i < parameterTypes.length; i++){
                    if (i == parameterTypes.length-1){
                        System.out.print(parameterTypes[i].getName() + "arg_" + i);
                    }
                    System.out.print(parameterTypes[i].getName() + "arg_" + i + ",");
                }

            }
            System.out.print(")");

            //6.抛出的异常
            Class[] exceptionTypes = m1.getExceptionTypes();
            if (exceptionTypes.length > 0){
                System.out.print("throw: ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName());
                    }
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }
    }
}
