package com.zzuli.java2;

import java.util.concurrent.*;

/**
 * 线程池 创建线程 开发最常用的方式
 * @auther pony
 * @create 2022-03-02 20:02
 */
class NumberThread2 implements Callable{

    private int sum = 0;
    @Override
    public Object call() throws Exception {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0){
                sum += i;
            }
        }
        return sum;
    }
}

class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 1){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadNew1 {
    public static void main(String[] args) {

//      1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

//      设置线程池的属性
        service1.setCorePoolSize(15);
//      service1.setKeepAliveTime();
        System.out.println(service.getClass());
//      2.执行指定的线程的操作,需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());//适用于Runnable
        service.execute(new NumberThread1());

//      3.service.submit();//

        NumberThread2 numberThread2 = new NumberThread2();
        FutureTask futureTask = new FutureTask(numberThread2);
        service.submit(futureTask);
        try {
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //关闭连接池
        service.shutdown();

    }

}
