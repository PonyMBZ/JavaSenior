package syntax;

/**
 * 可以快速的将一个 Lambda 表达式的实现指向一个已经实现的方法
 * 语法 ：方法的隶属者 ::方法名
 *
 *  **方法的隶属者 ：**
 *
 * - 如果一个方法声明是静态的，那么这个方法属于 所在类
 * - 如果一个方法声明是非静态的，那么这个方法属于 所在类的对象
 * @auther pony
 * @create 2022-04-28 21:48
 */
public class Lambda1 {
    private static interface Calculate{
        int calculate(int a, int b);
    }

    public static void main(String[] args) {
//        Calculate calculate = (x, y) -> calculate(x, y);
//        System.out.println(calculate.calculate(10, 20));

        Calculate calculate1 = Lambda1::calculate;
        System.out.println(calculate1.calculate(10, 10));
    }

    private static int calculate(int x, int y){
        if (x > y) {
            return x - y;
        }else if(x < y) {
            return y - x;
        }
        return x + y;
    }

    
}
