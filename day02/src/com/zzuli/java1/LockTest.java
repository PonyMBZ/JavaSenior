package com.zzuli.java1;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 解决线程安全问题三：Lock 锁 ——————JDK5.0新增
 * @auther pony
 * @create 2022-03-02 15:28
 */
class Window implements Runnable{

    private int ticket = 100;

//  1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
     @Override
    public void run() {

        while (true){
            try {
                //2.调用lock（），上锁
                lock.lock();
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
//              3.调用unlock(),解锁
                lock.unlock();
            }
        }


    }
}

public class LockTest {

    public static void main(String[] args) {
        Window window = new Window();
        Thread thread = new Thread(window);
        Thread thread2 = new Thread(window);
        thread.setName("窗口一");
        thread2.setName("窗口二");
        thread.start();
        thread2.start();
    }
}
