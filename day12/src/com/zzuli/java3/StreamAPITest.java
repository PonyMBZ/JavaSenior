package com.zzuli.java3;

import com.zzuli.java1.Employee;
import com.zzuli.java1.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. Stream 关注的是对数据的运算，与 CPU 打交道。
 *     集合   关注的是数据的存储，  与内存打交道。
 * 2.
 * ① Stream 自己不会存储元素
 * ② Stream 不会改变对象。相反，它们会返回一个持有结果的新的 Stream
 * ③ Stream 操作是延迟执行的，这意味着他们会等到需要结果的时候才执行
 *
 * 3. Stream 的执行操作
 * ① Stream 实例化
 * ② 一系列中间操作（过滤、映射---）
 * ③ 终止操作
 *
 * 4.说明
 * ① 一个中间操作链，对数据源的数据进行处理
 * ② 一旦执行终止操作，就执行中间操作链，并产生结果，之后，不会再被使用
 *
 * Stream的实例化 4种
 * @auther pony
 * @create 2022-03-26 14:48
 */
public class StreamAPITest {
//    方式一：通过集合创建
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();

        //default Stream<E> stream(): 返回一个顺序流（取用数据，根据 EmployeeData 中的写的数据顺序）
        Stream<Employee> stream = employees.stream();

        //default Stream<E> parallelStream(): 返回一个并行流（取用数据，类似与线程，数据顺序是不确定的）
        Stream<Employee> employeeStream = employees.parallelStream();
    }

//    方式二：通过数组创建
    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001, "Tom");
        Employee e2 = new Employee(1001, "Jerry");
        Employee[] employees = new Employee[]{e1, e2};
        Stream<Employee> stream1 = Arrays.stream(employees);

    }

//    方式三：通过 Stream 的 of()
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

    }

//    方式四：创建无限流
    @Test
    public void test4(){
        //迭代
        //遍历前是十个偶数
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out::println);

        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }


}
