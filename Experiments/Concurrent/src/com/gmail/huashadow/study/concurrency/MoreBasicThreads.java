package com.gmail.huashadow.study.concurrency;

/**
 * Created by wolf on 2017/3/13.
 * Thinking in Java 书上的例子
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for Liftoff");
    }
}
