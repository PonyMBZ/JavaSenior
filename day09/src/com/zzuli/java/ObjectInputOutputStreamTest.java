package com.zzuli.java;

import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 * @auther pony
 * @create 2022-03-19 15:24
 */
public class ObjectInputOutputStreamTest {
    /*
    * 序列化的过程
    * */
    @Test
    public void test1(){

        ObjectOutputStream oos = null;
        try {
            //此文件不是让直接看的，如果要看要用反序列化机制来看
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            oos.writeObject(new String("我爱北京天安门"));

            oos.flush();//刷新操作

            oos.writeObject(new Person("马炳政", 22));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 反序列化来看 “object.dat” 文件
    * */
    @Test
    public void test2(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object obj = ois.readObject();
            String str = (String) obj;

            Person p = (Person) ois.readObject();
            System.out.println(str);
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null){
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
