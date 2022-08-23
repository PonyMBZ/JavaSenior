package com.zzuli.java;

/**
 * 使用 enum 关键字定义 枚举类
 * @auther pony
 * @create 2022-03-06 15:53
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 spring = Season1.SPRING;
        System.out.println(spring);
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

    }

}
interface Info{
    public void show();
}
//枚举类
enum Season1 implements Info{
    //3.提供当前枚举类的多个对象
    SPRING("春天", "春暖花开"){
        @Override
        public void show() {
            System.out.println("春天");
        }
    },
    SUMMER("夏天", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("夏天");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天");
        }
    },
    WINTER("冬天", "冰天雪地"){
        @Override
        public void show() {
            System.out.println("冬天");
        }
    };

    //2.声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

    //1.私有化构造器
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //4.诉求一：获取属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }

//    //5.诉求二：提供toString()
//    @Override
//    public String toString() {
//        return "Season{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }
}
