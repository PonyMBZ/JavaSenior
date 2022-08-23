package com.zzuli.java;

/**
 * @auther pony
 * @create 2022-03-01 20:17
 */

class Window2 extends Thread{
    private static int ticket = 100;//必须加static不然相当于卖了300张票，因为new了3个对象

    private static Object obj = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (Window2.class){ //Class class = Window2.class, window2只会加载一次
            if(ticket > 0){
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": 买票，票号为：" + ticket);
                ticket --;
            }else{
                break;
            }
            }
        }
    }
}


public class WindowTest2 {
    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }

}
