package syntax;

import interfaces.NoneReturnMultipleParameter;
import interfaces.SingleReturnSingleParameter;

/**
 * lambda 语法的进阶
 * @auther pony
 * @create 2022-04-26 20:13
 */
public class BasicPro {
    public static void main(String[] args) {
        //实现一个参数，有返回值的函数式接口
        SingleReturnSingleParameter lambda1 = a -> a * a;

        //多个参数，无返回值的函数式接口
        NoneReturnMultipleParameter lambda2 = (a, b) -> System.out.println("这是一个多个参数，无返回值的函数式接口");

    }
}
