package com.zzuli.java;

/**
 * 线程创建方式一
 * @auther pony
 * @create 2022-02-28 14:57
 */

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            if (i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        MyThread myThread1 = new MyThread();
        myThread1.start();
        for (int i = 0; i <= 100; i++) {

            if (i % 2 == 0){
                System.out.println(i);
                System.out.println("**************************");
            }
        }

        Integer i = 129;
        Integer j = 129;
        System.out.println(i == j);
    }

}
