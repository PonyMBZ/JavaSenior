package com.zzuli.java;

/**
 * 表的共性操作
 * @auther pony
 * @create 2022-03-13 18:00
 */
public class Dao<T> {
    public void add(T t){ };
    public boolean remove(int index){return false;};
    public T getIndex(int index){return null;};

    public <E> E getValue(){
        return null;
    }

}
