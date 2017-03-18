package com.gmail.huashadow.study.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wolf on 2017/3/14.
 * 使用CachedThreadPool
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exector = Executors.newCachedThreadPool();
        for (int i=0; i<5;++i) {
            exector.execute(new LiftOff());
        }
        exector.shutdown();
    }
}