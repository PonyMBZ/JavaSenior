package com.zzuli.exer;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @auther pony
 * @create 2022-03-02 16:10
 */
class Account{

    private double balance = 0;

    private static ReentrantLock lock = new ReentrantLock();

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amt){
        try {
            lock.lock();
            if (amt > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance += amt;
                System.out.println(Thread.currentThread().getName()+ ":存钱成功,余额为:" + balance + "元");
            }
        }finally {
            lock.unlock();
        }

    }
}

class Customer extends Thread {
    private Account acc;



    public Customer(Account acc) {
        this.acc = acc;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            acc.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {

        Account account = new Account(0);
        Customer c1 = new Customer(account);
        Customer c2 = new Customer(account);
        c1.setName("甲");
        c2.setName("乙");
        c1.start();
        c2.start();
    }
}
