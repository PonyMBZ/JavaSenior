package com.zzuli.demo2;

/**
 * 泛型类的定义
 * <T> 泛型标识 —————— 类型形参
 * T 在创建对象的时候里指定具体的数据类型
 * @auther pony
 * @create 2022-04-24 18:04
 */
public class Generic<T> {
    public T Key;

    public Generic() {
    }

    public Generic(T key) {
        Key = key;
    }

    public T getKey() {
        return Key;
    }

    public void setKey(T key) {
        Key = key;
    }

    @Override
    public String toString() {
        return "Generic{" +
                "Key=" + Key +
                '}';
    }
}
