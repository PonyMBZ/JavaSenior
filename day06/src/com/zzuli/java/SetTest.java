package com.zzuli.java;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @auther pony
 * @create 2022-03-09 16:19
 */
public class SetTest {

    @Test
    public void test2(){

        TreeSet treeSet = new TreeSet();
        treeSet.add(new Person("Tom", 23));
        treeSet.add(new Person("Jerry", 32));
        treeSet.add(new Person("Jim", 26));
        treeSet.add(new Person("Mike", 46));
        treeSet.add(new Person("Jack", 33));
        treeSet.add(new Person("Jack", 23));

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
    @Test
    public void test1(){
        Set set= new HashSet();
        set.add(456);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new Person("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

}
