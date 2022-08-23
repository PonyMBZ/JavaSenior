package com.zzuli.java;

import org.junit.Test;

/**
 *
 * 关于StringBuffer 与 StringBuilder 的使用
 * @auther pony
 * @create 2022-03-04 8:08
 */
public class StringBufferBuilderTest {

    @Test
    public void test1(){
        StringBuffer s1 = new StringBuffer("abc");
        s1.append('l');
        s1.append(3);
        System.out.println(s1);
//        s1.delete(2, 4);
//        s1.replace(2,4, "Hello");
//        s1.insert(2, "hello");
//        s1.reverse();
        System.out.println(s1);

    }
}
