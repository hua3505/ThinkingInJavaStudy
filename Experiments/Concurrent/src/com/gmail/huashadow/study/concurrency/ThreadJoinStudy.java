package com.gmail.huashadow.study.concurrency;

/**
 * Created by wolf on 2017/3/30.
 * 研究Thread的join()方法
 */
public class ThreadJoinStudy {

    public static void main(String[] args) {
        Thread sleeper = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " has awakend");
        }, "Sleeper");
        Thread joiner = new Thread(() -> {
            try {
                synchronized (sleeper) {
                    System.out.println("join");
                    sleeper.wait();
                }

            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " join completed");
        }, "Joiner");
        sleeper.start();
        joiner.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (sleeper) {
            System.out.println("notifyAll");
            sleeper.notify();
        }
    }
}
