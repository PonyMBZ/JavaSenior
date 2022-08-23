package com.zzuli.exer;

import java.io.Serializable;

/**
 * @auther pony
 * @create 2022-03-22 19:11
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    private void eat(){
        System.out.println("生物吃东西");
    }
}
