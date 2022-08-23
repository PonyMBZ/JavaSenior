package com.zzuli.java;

/**
 * @auther pony
 * @create 2022-03-01 20:57
 */
class Window4 extends Thread{
    private static int ticket = 100;//必须加static不然相当于卖了300张票，因为new了3个对象

    @Override
    public void run() {
        while (true){
            show();
        }
    }
    public static synchronized void show(){
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 买票，票号为：" + ticket);
            ticket --;
        }
    }
}


public class WindowTest4 {
    public static void main(String[] args) {
        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }

}
