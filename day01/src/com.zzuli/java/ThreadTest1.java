package com.zzuli.java;

/**
 *
 * 线程创建方式二：
 * @auther pony
 * @create 2022-02-28 18:36
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        mThread mThread = new mThread();
        Thread t1 = new Thread(mThread);
        t1.start();
    }

}

class mThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}