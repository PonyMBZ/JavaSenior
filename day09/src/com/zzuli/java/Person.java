package com.zzuli.java;

import java.io.Serializable;

/**
 * @auther pony
 * @create 2022-03-19 15:51
 */
public class Person implements Serializable {

    public static final long serialVersionUID = 422324245L;//序列版本号

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
