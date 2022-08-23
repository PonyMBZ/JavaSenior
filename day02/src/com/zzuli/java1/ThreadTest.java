package com.zzuli.java1;


/**
 * 线程死锁问题
 * @auther pony
 * @create 2022-03-02 15:10
 */
public class ThreadTest {
    public static void main(String[] args) {
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();

        new Thread(){
            @Override
            public void run() {
                synchronized (stringBuffer1){
                    stringBuffer1.append("a");
                    stringBuffer2.append("1");
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (stringBuffer2){
                        stringBuffer1.append("b");
                        stringBuffer2.append("2");
                        System.out.println(stringBuffer1);
                        System.out.println(stringBuffer2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (stringBuffer1){
                    stringBuffer1.append("a");
                    stringBuffer2.append("1");
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (stringBuffer2){
                        stringBuffer1.append("b");
                        stringBuffer2.append("2");
                        System.out.println(stringBuffer1);
                        System.out.println(stringBuffer2);
                    }
                }
            }
        }).start();
    }
}
