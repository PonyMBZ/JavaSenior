package com.zzuli.java2;

/**
 * 线程通信 多线程交替打印100个数
 * @auther pony
 * @create 2022-03-02 16:44
 */
class Number implements Runnable{

    private int number = 1;
    @Override
    public void run() {
        while (true){

            synchronized (this) {
                notifyAll();
                if(number <= 100){
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}
public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);

        thread1.setName("打印机1号");
        thread2.setName("打印机2号");

        thread1.start();
        thread2.start();


    }

}
