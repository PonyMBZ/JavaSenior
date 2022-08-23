package com.zzuli.java;

/**
 * 测试线程的一些常用方法
 * @auther pony
 * @create 2022-02-28 16:37
 */
public class ThreadMethodTest {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.setName("线程一");
        myThread1.start();
        System.out.println(myThread1.isAlive());
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            for(int i = 0; i <= 100; i++) {
                if (i % 2 == 0) {
                    System.out.println("主线程"  + Thread.currentThread().getPriority() + ":" + i);
                }
                if(i == 20){
                    try {
                        myThread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        System.out.println(myThread1.isAlive());
    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i <= 100; i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getPriority() + ":" + i);
            }
            if(i % 20  == 0){
                yield();
            }
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class MyTread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getPriority() + ":" + i);
            }
            if(i % 20  == 0){
                yield();
            }

        }

    }

    public MyTread2(String name) {
        super(name);
    }

}

