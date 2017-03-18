package com.gmail.huashadow.study.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wolf on 2017/3/14.
 * 使用SingleThreadPool
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService exector = Executors.newSingleThreadExecutor();
        for (int i=0; i<5;++i) {
            exector.execute(new LiftOff());
        }
        exector.shutdown();
    }
}
