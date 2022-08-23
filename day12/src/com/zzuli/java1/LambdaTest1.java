package com.zzuli.java1;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的使用
 * 1.举例： （o1, o2） -> Integer.compare(o1, o2)
 * 2.格式：
 *     ->: Lambda 操作符 或 箭头操作符
 *     ->左边: lambda形参列表（其实就是接口中的抽象方法的形参列表）
 *     ->右边：lambda体（其实就是我们从写的抽象方法的方法体）
 * 3.Lambda表达式的使用：6种情况
 *
 *     总结：
 *     ->左边: lambda形参列表的参数类型可以省略（类型推断）；只有一个参数时，参数的小括号可以省略
 *     ->右边：lambda体应该使用一对{}进行包裹；Lambda体只有一条语句对，return 与大括号如果有都可以省略
 *
 * 4.Lambda表达式的本质：作为函数式接口的实例
 *
 * 5.函数式接口：如果一个接口，只声明了一个抽象方法，则此接口就称为函数式接口，可以使用 @FunctionalInterface注解，这样做可以检查它是否是一个函数式接口
 * @auther pony
 * @create 2022-03-25 16:36
 */
public class LambdaTest1 {
    //方式一：无参，无返回值
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

        Runnable r2 = () -> {
            System.out.println("我爱北京故宫");
        };

        r2.run();
    }

    //方式二：Lambda表达式需要一个参数，但是没有返回值
    @Test
    public void test2(){
        Consumer<String> con = new Consumer<String>() {

            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("谎言和誓言的区别是什么呢？");

        System.out.println("-----------------------------------------");

        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("一个是听的人当真了，一个是说的人当真了。");

    }

    //方式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void test3(){
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("一个是听的人当真了，一个是说的人当真了。");

        System.out.println("-----------------------------------------");

        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con2.accept("一个是听的人当真了，一个是说的人当真了。");

    }

    //方式四：Lambda 诺只需要一个参数时，参数的小括号可以省略
    @Test
    public void test4(){
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("一个是听的人当真了，一个是说的人当真了。");

        System.out.println("-----------------------------------------");

        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("一个是听的人当真了，一个是说的人当真了。");
    }

    //方式五：Lambda表达式需要两个或以上的参数，执行多条语句，并且可以有返回值
    @Test
    public void test5(){
        Comparator<Integer> com1 = new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return Integer.compare(o1,o2);
            }
        };
        System.out.println(com1.compare(12, 21));

        System.out.println("-----------------------------------------");

        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1,o2);
        };

        System.out.println(com2.compare(12, 6));
    }

    //方式六：Lambda体只有一条语句对，return 与大括号诺有都可以省略
    @Test
    public void test6(){
        Comparator<Integer> com1 = (o1, o2) -> {
            return Integer.compare(o1,o2);
        };

        System.out.println(com1.compare(12, 6));

        System.out.println("-----------------------------------------");

        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1,o2);
        System.out.println(com2.compare(12, 6));

    }

}
