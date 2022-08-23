package com.zzuli.java;

import java.util.Objects;

/**
 * @auther pony
 * @create 2022-03-07 16:12
 */
public class Person implements Comparable{
    public Person() {
    }

    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private int age;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person){
            Person p = (Person) o;
            int compare =  -this.name.compareTo(p.name);
            if (compare != 0){
                return compare;
            }else {
                return Integer.compare(this.age, p.age);
            }
        }else {
            throw new RuntimeException("输入的类型不匹配!");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }


}
