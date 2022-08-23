package com.zzuli.exer;

import org.junit.Test;

import java.util.HashSet;

/**
 * 关于 HashSet 的面试题
 * @auther pony
 * @create 2022-03-09 19:46
 */
public class CollectionTest {
    @Test
    public void test(){

        HashSet set = new HashSet();

        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);

        //1.这里的CC用的是原来 1001，“AA” 哈希值1计算得到的数组位置1
        p1.name = "CC";
        /*这里的删除p1,它会先将改完后的 1001，“CC” 的哈希值2计算得到数组位置2，并将数组位置2元素删除，
        * 而哈希值1 != 哈希值2，所以并没有删除哈希值1001，“CC”。
        * */
        set.remove(p1);
        System.out.println(set);

        /*2.先将 1001，“CC” 的哈希值2计算得到数组位置2，并向数组位置2添加元素，数组位置2本来就没有元素，
        所以是能添加成功的。
        */
        set.add(new Person(1001,"CC"));
        System.out.println(set);

        /*3.先将 1001，“AA” 的哈希值1计算得到数组位置1，并向数组位置1添加元素，数组位置1有元素，
        * 1001 “CC”,然后会比较（equals()）1001,"CC" 与 1001 “AA”,发现不一样可以添加，以链表的
        * 形式添加到数组位置1的下面。
        * */
        set.add(new Person(1001,"AA"));
        System.out.println(set);

    }
}
