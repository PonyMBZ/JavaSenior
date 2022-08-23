package com.zzuli.java;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 * @auther pony
 * @create 2022-03-21 21:20
 */
public class NewInstanceTest {
    @Test
    public void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<Person> clazz = (Class<Person>) Class.forName("com.zzuli.java.Person");

//        newInstance()
        Person person = clazz.newInstance();
        System.out.println(person);

    }

    @Test
    public void test2(){

        for (int i = 0; i < 100; i++) {
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.zzuli.java.Person";
                    break;
            }

            try {
                Object instance = getInstance(classPath);
                System.out.println(instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
    /*
    * 创建一个指定类的对象
    * classPath: 指定类的全类名
    * */
    public Object getInstance(String classPath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }



}
