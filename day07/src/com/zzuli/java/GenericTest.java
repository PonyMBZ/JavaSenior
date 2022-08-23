package com.zzuli.java;

import org.junit.Test;

import java.util.*;

/**
 * 泛型的使用
 * @auther pony
 * @create 2022-03-13 15:58
 */
public class GenericTest {

    //在集合中使用泛型之前的情况
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        //要求存放学生的成绩
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);
        //问题一：类型不安全
        list.add("Tom");

        for (Object score: list){
//          问题二：强转是，可能出现ClassCastException("Tom",是肯定不会转成Integer)
            int stuScore = (Integer) score;
            System.out.println(stuScore);
        }

    }

    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        //编译时，就会进行类型检查，保证数据的安全
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);
//      避免了强转
//        for (Integer score: list) {
//            int stuScore = score;
//            System.out.println(stuScore);
//        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3(){
        Map<String, Integer> map = new HashMap<String,Integer>();

        map.put("Tom", 13);
        map.put("Jerry", 26);
        map.put("Jack", 45);

        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();

        while (iterator.hasNext()){

            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();

            System.out.println("key = " + key + "\t,value = " + value);
        }


    }
}
