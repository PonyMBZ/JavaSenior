package com.zzuli.java;

/**
 * 使用接口的方式实现线程
 *
 * 解决线程安全问题
 * 1.安全问题出现的原因：当某个线程操作车票的过程中，尚未完成操作，其他线程就参与进来了
 * 2.解决方案：当某个线程操作车票的过程中，其他线程不能参与进来，直到线程操作车票完成
 *          才能进来，这种情况即使线程出现了阻塞也不能改变
 * 3.在Java中，我们通过同步机制，来解决线程安全问题
 *
 * 方式一：同步代码块
 * synchronized(同步监视器){
 *     //需要被同步的代码
 *
 * }
 * 说明：1.操作共享数据的代码，即为需要被同步的代码
 *      2.共同数据，多个线程共同操作的变量
 *      3.同步监视器，俗称 ‘锁’ ，任何一个类的对象都可以充当锁。
 *      要求：多个线程必须要用同一把锁
 *
 *
 * 方式二：同步方法
 *      如果操作共享数据的代码声明在一个方法中，我们不妨将此方法声明同步的
 * @auther pony
 * @create 2022-02-28 19:23
 */
public class WindowTest1 {

    public static void main(String[] args) {
        Window1 window1 = new Window1();
        Thread t1 = new Thread(window1);
        Thread t2 = new Thread(window1);
        Thread t3 = new Thread(window1);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Window1 implements Runnable{
    private int tictak = 100;//可以不加static，因为只new了一个对象

    Object obj = new Object();
    @Override
    public void run() {
        while (true){
            synchronized(this){
                if(tictak > 0){
                   try {
                    Thread.sleep(100);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + tictak);
                    tictak--;
                }else {
                    break;
            }
            }
        }
    }
}