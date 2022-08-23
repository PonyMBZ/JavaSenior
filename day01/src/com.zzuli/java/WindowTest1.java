package com.zzuli.java;

/**
 * 使用接口的方式实现线程
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

    @Override
    public void run() {
        while (true){
            if(tictak > 0){
                System.out.println(Thread.currentThread().getName() + ":" + tictak);
                tictak--;
            }else {
                break;
            }
        }
    }
}