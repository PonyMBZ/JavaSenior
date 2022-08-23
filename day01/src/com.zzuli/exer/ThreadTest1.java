package com.zzuli.exer;

/**
 * @auther pony
 * @create 2022-03-01 17:16
 */
public class ThreadTest1 {

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.setName("进程一");
        thread1.start();
        
        Thread2 thread2 = new Thread2();
        Thread t = new Thread(thread2);
        t.setName("进程二");
        t.start();
    }
}

class Thread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + i);
            }
        }
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + i);
            }
        }
    }
}
