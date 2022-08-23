package com.zzuli.java2;

/**
 * 经典题: 生产者与消费者问题
 * @auther pony
 * @create 2022-03-02 17:46
 */
class Clerk{

    private int productNum = 0;
    //生产产品
    public synchronized void produceProduct() {
        if(productNum < 20){
            productNum++;
            System.out.println(Thread.currentThread().getName() + ":开始生产第" + productNum + "产品");
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //消费产品
    public synchronized void consumerProduct() {
        if (productNum > 0){
            System.out.println(Thread.currentThread().getName() + ":开始消费第" + productNum + "产品");
            productNum--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//生产者
class Producer1 extends Thread{

    private Clerk clerk;

    public Producer1(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":开始生产产品............");
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

//消费者
class Consumer1 extends Thread{

    private Clerk clerk;

    public Consumer1(Clerk clerk) {
        this.clerk = clerk;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":开始消费产品............");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumerProduct();
        }
    }
}

public class ProductTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer1 producer = new Producer1(clerk);
        Consumer1 consumer = new Consumer1(clerk);

        producer.setName("生产者1");
        consumer.setName("消费者1");

        producer.start();
        consumer.start();
    }
}
