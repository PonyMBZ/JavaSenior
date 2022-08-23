package com.zzuli.pony;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @auther pony
 * @create 2022-05-17 19:07
 */
public class Test1 {
    class p{
        public String name;

        public p() {
        }

        public p(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "p{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    @Test
    public void text(){
        String[][] a = new String[3][3];
        LinkedList<p> pLinkedList = new LinkedList<>();
        pLinkedList.add(new p("pony"));
        pLinkedList.add(new p("hanser"));
        for (p p1: pLinkedList) {
            System.out.println(p1);
            boolean f = p1.name.equals("pony");
            System.out.println(f);
        }
    }
static class XY{
        int x;
        int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
    @Test
    public void mapText(){
        LinkedHashMap<Integer, XY> map = new LinkedHashMap<>();
        map.put(1,new XY(2, 3));
        map.put(2,new XY(2,4));
        map.put(5,new XY(3,5));

        XY xy = map.get(2);
        System.out.println(xy.x);
        System.out.println(xy.y);
    }
}
