package com.zzuli.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @auther pony
 * @create 2022-03-06 11:11
 */
public class CompareTest {

    @Test
    public void test3(){
        String[] arr = new String[]{"AA", "CC", "GG", "MM", "JJ", "KK", "DD"};
        Arrays.sort(arr, new Comparator<String>() {
            //按照从大到小排序
            @Override
            public int compare(String o1, String o2) {
                if (o1 instanceof String && o2 instanceof String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("输入的数据类型异常！");
            }
        });
        System.out.println(arr);
    }

    @Test
    public void test2(){

        Goods[] goods = new Goods[4];
        goods[0] = new Goods("LianXianMouse", 34);
        goods[1] = new Goods("DellMouse", 43);
        goods[2] = new Goods("XiaoMiMouse", 12);
        goods[3] = new Goods("HuaWeiMouse", 65);

        Arrays.sort(goods);

        System.out.println(Arrays.toString(goods));
        Arrays.sort(goods, new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;
                    if(g1.getName().equals(g2.getName())){
                        return -Double.compare(g1.getPrice(), g2.getPrice());

                    }else{
                        return g1.getName().compareTo(g2.getName());
                    }
                }
                throw new RuntimeException("输入的数据类型异常！");
            }
        });
    }
    /*
       Comparable 的使用:

     */
    @Test
    public void test1(){

        String[] arr = new String[]{"AA", "CC", "GG", "MM", "JJ", "KK", "DD"};



        System.out.println(Arrays.toString(arr));
    }
}
