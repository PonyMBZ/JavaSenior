package com.zzuli.exer;

import com.zzuli.java.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 有限制的通配符的使用
 * @auther pony
 * @create 2022-03-14 16:29
 */
public class GenericTest2 {
    @Test
    public void test1(){
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;
        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        list1 = list3;
        Person person = list1.get(0);
//        Student student = list1.get(0);

        list2 = list4;
//        Person person1 = list2.get(0);
        Object object = list2.get(0);

        //写数据：
    }
}
