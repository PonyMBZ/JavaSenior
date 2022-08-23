package com.zzuli.exer;

/**
 * @auther pony
 * @create 2022-03-22 19:09
 */
@MyAnnotation(value = "hi")
public class Person extends Creature<String> implements Comparable<String>,MyInterface{

    private String name;
    int age;
    public int id;

    public Person(){}

    @MyAnnotation(value = "ABC")
    private Person(String name){
        this.name = name;
    }

    Person(String name, int age) {
        this.name = name;
        this.id = id;
    }

    public String display(String interests) throws NullPointerException, ClassCastException{
        return interests;
    }

    @MyAnnotation(value = "hi")
    private String show(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    private static void showDesc(){
        System.out.println("我是一个可爱的人！");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
