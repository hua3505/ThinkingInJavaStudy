package com.gmail.huashadow.study.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by wolf on 2017/3/29.
 * Daemon 线程研究
 */
public class DaemonThreadStudy {

    private static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    }

    private static class DaemonFromFactory implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread() + " " + this);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            executor.execute(new DaemonFromFactory());
        }
        System.out.println("All daemons started");
        Thread.sleep(100);
    }
}
