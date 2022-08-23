package com.zzuli.java;

/**
 * 使用同步方法解决线程安全问题
 * @auther pony
 * @create 2022-03-01 20:43
 */
public class WindowTest3 {

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

class Window3 implements Runnable{
    private int tictak = 100;//可以不加static，因为只new了一个对象

    @Override
    public void run() {

        boolean isFlag = true;
        while (isFlag){

            isFlag = show();

        }
    }
    public synchronized boolean show(){

            if(tictak > 0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + tictak);
                tictak--;
                return true;
            }else{
                return false;
            }

    }

}
