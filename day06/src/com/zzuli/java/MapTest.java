package com.zzuli.java;

import org.junit.Test;

import java.util.*;

/**
 * HashMap 方法测试
 * @auther pony
 * @create 2022-03-09 21:19
 */
public class MapTest {
    @Test
    public void test5(){
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;
                    return Integer.compare(p1.getAge(),p2.getAge());

                }
            throw new RuntimeException("输入的类型不匹配！");
            }
        });
        Person p1 = new Person("Tom", 23);
        Person p2 = new Person("Jerry", 32);
        Person p3 = new Person("jack", 20);
        Person p4 = new Person("Rose", 18);
        treeMap.put(p1, 98);
        treeMap.put(p2, 89);
        treeMap.put(p3, 76);
        treeMap.put(p4,100);

        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
//            System.out.println(iterator.next());
            Object next = iterator.next();
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }
    }

    @Test
    public void test4(){
        TreeMap treeMap = new TreeMap();
        Person p1 = new Person("Tom", 23);
        Person p2 = new Person("Jerry", 32);
        Person p3 = new Person("jack", 20);
        Person p4 = new Person("Rose", 18);
        treeMap.put(p1, 98);
        treeMap.put(p2, 89);
        treeMap.put(p3, 76);
        treeMap.put(p4,100);

        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
//            System.out.println(iterator.next());
            Object next = iterator.next();
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }
    }


    @Test
    public void test3(){

        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        map.put("AA", 87);

        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("\n");
        Collection values = map.values();
        for(Object obj: values){
            System.out.println(obj);
        }
        System.out.println("\n");
        Set set1 = map.entrySet();
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()){
            Object next = iterator1.next();
            Map.Entry entry = (Map.Entry)next;
            System.out.println(entry.getKey() + "------>" + entry.getValue());
        }
        System.out.println("\n");
        Set set2 = map.keySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()){
            Object next = iterator2.next();
            Object o = map.get(next);
            System.out.println(next + "------>" + o);
        }


    }

    @Test
    public void test2(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        map.put("AA", 87);

        System.out.println(map.get(45));
        boolean bb = map.containsKey("BB");
        System.out.println(bb);
        boolean b = map.containsValue(123);
        System.out.println(b);

        System.out.println(map.size());
        map.clear();
        System.out.println(map.isEmpty());


    }
    @Test
    public void test1(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        map.put("AA", 87);
        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("CC", 123);
        map1.put("DD", 123);

        map.putAll(map1);
        System.out.println(map);
        Object cc = map.remove("CC");
        System.out.println(cc);
        System.out.println(map);

        map.clear();
        System.out.println(map.size());
        System.out.println(map);
    }



}
