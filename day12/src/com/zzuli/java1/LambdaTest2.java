package com.zzuli.java1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java 内置的四大核心函数式接口
 * 消费型接口：Consumer<T>        void appept(T t)
 * 供给型接口：Supplier<T>        T get()
 * 函数型接口：Function<T , R>    R apply(T t)
 * 断定型接口：Predicate<T>       boolean test(T t)
 *
 * @auther pony
 * @create 2022-03-25 18:06
 */
public class LambdaTest2 {
   @Test
   public void test1(){
       happyTime(500, new Consumer<Double>() {
           @Override
           public void accept(Double aDouble) {
               System.out.println("学习太累了，去天上人间买了瓶矿泉水，价格为：" + aDouble);
           }
       });

       System.out.println("*****************************");

       happyTime(400, aDouble -> System.out.println("学习太累了，去天上人间河流水，价格为：" + aDouble));
   }

   public void happyTime(double money, Consumer<Double> con){
       con.accept(money);
   }

   @Test
   public void test2(){
       List<String> list = Arrays.asList("北京", "南京", "天津", "西京", "东京", "普京");
       List<String> filterString = filterString(list, new Predicate<String>() {
           @Override
           public boolean test(String s) {
               return s.contains("京");
           }
       });

       System.out.println(filterString);

       List<String> filterString1 = filterString(list, s -> s.contains("京"));

       System.out.println(filterString1);
   }

   //根据给定的规则，过滤集合中的字符串，此规则由 Predicate 指定
   public List<String> filterString(List<String> list, Predicate<String> pre){

       ArrayList<String> filterList = new ArrayList<>();
       for (String s: list){
           if (pre.test(s)){
               filterList.add(s);
           }
       }

       return filterList;
   }


}
