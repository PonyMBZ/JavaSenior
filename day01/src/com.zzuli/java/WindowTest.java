package com.zzuli.java;

/**
 *
 * 创建三个窗口买票，总票数为100张
 * @auther pony
 * @create 2022-02-28 18:11
 */
class Window extends Thread{
    private static int ticket = 100;//必须加static不然相当于卖了300张票，因为new了3个对象

    @Override
    public void run() {
        while (true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + ": 买票，票号为：" + ticket);
                ticket --;
            }else{
                break;
            }
        }
    }
}


public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口一");
        w2.setName("窗口一");
        w3.setName("窗口一");

        w1.start();
        w2.start();
        w3.start();
    }

}
