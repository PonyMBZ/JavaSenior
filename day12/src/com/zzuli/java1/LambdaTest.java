package com.zzuli.java1;

import org.junit.Test;

import java.util.Comparator;

/**
 * @auther pony
 * @create 2022-03-25 16:18
 */
public class LambdaTest {
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {

            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };

        r1.run();

        System.out.println("-----------------------------------------");

        //Lambda写法

        Runnable r2 = () -> System.out.println("我爱北京故宫");

        r2.run();
    }

    @Test
    public void test2(){
        Comparator<Integer> com1 = new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        int compare1 = com1.compare(12, 21);
        System.out.println(compare1);

        System.out.println("-----------------------------------------");

        Comparator<Integer> com2 = (o1,o2) -> Integer.compare(o1,o2);

        int compare2 = com2.compare(32, 21);
        System.out.println(compare2);

        System.out.println("-----------------------------------------");

        Comparator<Integer> com3 = Integer :: compare;

        int compare3 = com3.compare(32, 21);
        System.out.println(compare3);
    }

}
