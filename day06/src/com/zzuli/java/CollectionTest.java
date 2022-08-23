package com.zzuli.java;

import org.junit.Test;

import java.util.*;

/**
 * 集合测试
 * @auther pony
 * @create 2022-03-07 14:39
 */
/*
   Collection接口中的方法的使用


*/
public class CollectionTest {

    @Test
    public void test7(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add(456);
        arrayList.add("AA");
        arrayList.add(new Person("Tom",13));
        arrayList.add(456);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for (Object o: arrayList){
            System.out.println(o);
        }

    }

    @Test
    public void test6(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add(456);
        arrayList.add("AA");
        arrayList.add(new Person("Tom",13));
        arrayList.add(456);
        System.out.println(arrayList);

        //1.void add(int index, Object ele):在index 位置插入ele元素
        arrayList.add(1, "BB");
        System.out.println(arrayList);
        
        //2.addAll();
        List<Integer> integers = Arrays.asList(1, 2, 3);
        arrayList.addAll(integers);
        System.out.println(arrayList.size());
        System.out.println(arrayList);

        //get()
        System.out.println(arrayList.get(2));

        //indexOf()
        System.out.println(arrayList.indexOf("BB"));

        //lastIndex()
        System.out.println(arrayList.lastIndexOf(456));

        //remove()
        Object remove = arrayList.remove(0);
        System.out.println(remove);
        System.out.println(arrayList);

        //subList()
        System.out.println(arrayList.subList(1, 4));
    }
    @Test
    public void test5(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new Person("Jerry", 20));
        for (Object obj: coll) {
            System.out.println(obj);
        }

        int[] arr = new int[]{123, 456, 789};
        for (int i: arr) {
            System.out.println(i);
        }
    }
    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new Person("Jerry", 20));

        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();
            }
        }
        Iterator iterator1 = coll.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
    }
    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new Person("Jerry", 20));

        Iterator iterator = coll.iterator();

//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new Person("Jerry", 20));

//      contains():
        System.out.println(coll.contains(123));
        System.out.println(coll.contains(new String("Tom")));
        System.out.println(coll.contains(new Person("Jerry", 20)));

        //remove(Object obj):
        System.out.println(coll.remove(123));
        System.out.println(coll);
        //removeAll(Collection coll1)
        Collection coll1 = Arrays.asList(123, 456);
        coll.removeAll(coll1);
        System.out.println(coll);
    }
    @Test
    public void test1(){
        //add(Object e)方法：将元素e添加到集合coll中
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new Date());
        //size():获取添加的元素的个数
        System.out.println(coll.size());

        //addAll(Collection coll1):将coll1集合中的元素添加到当前集合中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);
        coll.add(new Person("Tom", 21));

        System.out.println(coll.size());//6
        System.out.println(coll);

        //clear()；清空集合
        coll.clear();

        //isEmpty():判断当前集合是否为空
        System.out.println(coll.isEmpty());//为空返回 true
    }
}
