package com.zzuli.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auther pony
 * @create 2022-03-13 14:49
 */
public class CollectionsTest {
    @Test
    public void test(){
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);

        System.out.println(list);
//        Collections.reverse(list);
        Collections.shuffle(list);
        System.out.println(list);
        List list1 = Collections.synchronizedList(list);

    }

}
