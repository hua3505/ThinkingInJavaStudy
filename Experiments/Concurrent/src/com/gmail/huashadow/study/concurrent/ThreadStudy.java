package com.gmail.huashadow.study.concurrent;

/**
 * Created by wolf on 2017/3/13.
 * 研究Thread类
 */
public class ThreadStudy {
    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            new Thread(new SimpleRunable()).start();
        }
    }

    private static class SimpleRunable implements Runnable {
        @Override
        public void run() {
            System.out.print(Thread.currentThread().getName() + ",");
//            Thread.yield();
            System.out.print(Thread.currentThread().getName() + ",");
//            Thread.yield();
            System.out.print(Thread.currentThread().getName() + ",");
        }
    }
}
