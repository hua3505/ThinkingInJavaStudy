package com.gmail.huashadow.study.concurrency.sharedresources;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wolf on 2017/4/15.
 * 研究 Synchronized
 */
public class SynchronizedStudy {

}


/**
 * 研究对Integer做synchronized安不安全，实际上就是看Integer的对象会不会中途变成另一个对象
 * 下面这样的方式是安全的
 */
class SynchronizedOnInteger {

    private static Integer sNum = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                synchronized (sNum) {
                    System.out.println(Thread.currentThread().getName() + " " + sNum);
                    try {
                        Thread.sleep(1000);
                        sNum = 10;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
        executor.shutdown();
    }
}
