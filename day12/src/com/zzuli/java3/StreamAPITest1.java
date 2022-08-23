package com.zzuli.java3;

import com.zzuli.java1.Employee;
import com.zzuli.java1.EmployeeData;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 的中间操作
 * @auther pony
 * @create 2022-03-26 16:01
 */
public class StreamAPITest1 {

    //1.筛选与切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
//  filter(Predicate p): 接收 Lambda. 从流中排除某些元素
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println("--------------------");
//  limit: 截断流, 使其元素不超过给定的数量
        list.stream().limit(3).forEach(System.out::println);
        System.out.println("--------------------");
//  skep(n): 跳过元素，返回一个扔掉了前 n 个元素的流。如果流中元素不足 n 个，则返回一个空流
        list.stream().skip(3).forEach(System.out::println);
        list.stream().skip(8).forEach(System.out::println);
        System.out.println("--------------------");
//  distinct(): 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素,要重写equals()和hashCode()
        list.add(new Employee(1010, "刘强东", 40,8000));
        list.add(new Employee(1010, "刘强东", 40,8000));
        list.add(new Employee(1010, "刘强东", 40,8000));
        list.add(new Employee(1010, "刘强东", 40,8000));
        list.add(new Employee(1010, "刘强东", 40,8000));
        list.stream().distinct().forEach(System.out::println);
    }

    //映射
    @Test
    public void test2(){
         double d1 = 16;
         double d2 = 5;
        System.out.println(d1 / d2);
    }
}
