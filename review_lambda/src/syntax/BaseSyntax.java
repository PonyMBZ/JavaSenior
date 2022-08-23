package syntax;

import interfaces.*;

/**
 * lambda 表达式的基本语法
 * @auther pony
 * @create 2022-04-26 19:37
 */
public class BaseSyntax {
    public static void main(String[] args) {
        //1、无参无返回值的函数式接口
        NoneReturnNoneParameter lambda1 = () -> {
            System.out.println("这是一个无参，无返回值的方法！");
        };
        lambda1.test();

        //2、实现一个有参，无返回值的接口
        NoneReturnSingleParameter lambda2 = (int a) ->{
            System.out.println("这是一个有参，无返回值的方法！参数a = " + a);
        };
        lambda2.test(1);

        //3、实现多个参数，无返回值的接口
        NoneReturnMultipleParameter lambda3 = (int a, int b) ->{
            System.out.println("这是一个多个参数，无返回值的方法！参数a = " + a + ", 参数b = " + b);
        };
        lambda3.test(1,2);

        //4、实现无参数，有返回值的接口
        SingleReturnNoneParameter lambda4 = () ->{
          System.out.println("这是一个无参数，有返回值的方法！返回值是" + 10);
          return 10;
        };
        int re4 = lambda4.test();
        System.out.println(re4);

        //5、实现有一个参数。有返回值的接口
        SingleReturnSingleParameter lambda5 = (int a) ->{
            System.out.println("这是一个有一个参数，有返回值的方法！返回值 = 参数 = " + a);
            return a;
        };
        int re5 = lambda5.test(10);
        System.out.println(re5);

        //6、实现有多个参数，有返回值的接口
        SingleReturnMultipleParameter lamdba6 = (int a, int b) ->{
            System.out.println("这是多个参数，有返回值的方法！返回值 = 参数 a + b = " + (a+b) );
            return a + b;
        };
        int re6 = lamdba6.test(100, 10);
        System.out.println(re6);
    }


}

