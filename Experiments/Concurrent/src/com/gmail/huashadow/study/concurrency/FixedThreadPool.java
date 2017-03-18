package com.gmail.huashadow.study.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wolf on 2017/3/14.
 * 使用FixedThreadPool
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exector = Executors.newFixedThreadPool(3);
        for (int i=0; i<5;++i) {
            exector.execute(new LiftOff());
        }
        exector.shutdown();
    }
}


