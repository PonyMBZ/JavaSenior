package functionalInterface;

/**
 * 函数式接口，即一个接口中，要求实现的类必须实现的抽象方法，**有且只有一个**！这样的接口，就是函数式接口。
 * @auther pony
 * @create 2022-04-26 18:38
 */
public class InterfaceTest {
    //是函数式接口：这个接口中有且只有一个方法，而且是实现类必须实现的
    @FunctionalInterface
    interface Test1{
        void test1();
    }

    //不是函数式接口：这个接口中必须要实现的方法有 两 个
    interface Test2{
        void test1();
        void test2();
    }

    //不是函数式接口：这个接口中必须要实现的方法有 零 个
    interface Test3{

    }

    //是函数式接口：这个接口自己虽然没有定义方法，但可以从父类中继承到一个抽象方法
    interface Test4 extends Test1{

    }

    //是函数式接口：这个接口虽然定义了两个方法，但 default 修饰的方法不是必须实现的方法
    //default修饰方法只能在接口中使用，在接口中被default标记的方法为普通方法，可以直接写方法体,可以实现也可以不实现可以重写也可以不重写。
    interface Test5 {
        void test1();
        default void test2(){};
    }

    //是函数式接口
    //toString() 方法,是 Object 类中定义的方法
    //实现类在实现接口中，toString() 方法可以不重写，因为可以从父类 Object 中继承到
    interface Test6{
        void test1();
        String toString();
    }

    //是函数式接口：静态方法不用被重写
    interface Test7{
        void test1();
        static void test2(){};
    }
}
