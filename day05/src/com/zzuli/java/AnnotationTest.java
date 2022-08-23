package com.zzuli.java;

/**
 * @auther pony
 * @create 2022-03-06 17:32
 */
public class AnnotationTest {

}
@MyAnnotation(value = "hi~")
class Person{
    private String name;
    private String age;

    public Person() {
    }

    public Person(String name, String age) {
        this.name = name;
        this.age = age; 
    }

    @SuppressWarnings({"unused"})
    public void walk(){
        System.out.println("人走路");
    }
    public void eat(){
        System.out.println("人吃饭");
    }

}
interface Info1{
    void show();
}
class Student extends Person implements Info1{
    @Override
    public void walk(){
        System.out.println("学生走路");
    }

    @Override
    public void show() {

    }
}