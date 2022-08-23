package interfaces;

/**
 * 有返回值，一个参数的 函数式接口
 * @auther pony
 * @create 2022-04-26 19:28
 */
@FunctionalInterface
public interface SingleReturnSingleParameter {
    int test(int a);
}
