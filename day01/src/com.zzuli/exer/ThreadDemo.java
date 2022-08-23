package com.zzuli.exer;

/**
 *
 * 练习：创建两个线程，一个遍历100以内的偶数，一个遍历100以内的奇数
 * @auther pony
 * @create 2022-02-28 15:49
 */
public class ThreadDemo {

    public static void main(String[] args) {
//        方式一：
//        MyThread1 myThread1 = new MyThread1();
//        MyThread2 myThread2 = new MyThread2();
//        myThread1.start();
//        myThread2.start()
//        方式二：匿名子类
        new Thread(){
           @Override
           public void run(){
               for(int i = 0; i <= 100; i++){
                   if(i % 2 == 0){
                       System.out.println(Thread.currentThread().getName() + ":" + i);
                   }
               }
           }
        }.start();

        new Thread(){
            @Override
            public void run(){
                for(int i = 0; i <= 100; i++){
                    if(i % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();
    }
}

//遍历100以内的偶数
class MyThread1 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i <= 100; i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}

//一个遍历100以内的奇数
class MyThread2 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i <= 100; i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}