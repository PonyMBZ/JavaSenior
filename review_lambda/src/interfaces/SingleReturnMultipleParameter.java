package interfaces;

/**
 * 有返回值，多个参数的函数式接口
 * @auther pony
 * @create 2022-04-26 19:30
 */
@FunctionalInterface
public interface SingleReturnMultipleParameter {
    int test(int a, int b);
}
