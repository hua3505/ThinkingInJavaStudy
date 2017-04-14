package com.gmail.huashadow.study.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by wolf on 2017/4/10.
 * 研究线程的未被捕获异常处理
 */
public class ThreadUncaughtExecptionStudy {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new ExceptionRunable());
    }
}

class ExceptionRunable implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }
}

class NativeExceptionHandling {
    public static void main(String[] args) {
        try {
            Executor executor = Executors.newCachedThreadPool();
            executor.execute(new ExceptionRunable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return thread;
    }
}

class CaptureUncaughtException {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executor.execute(new ExceptionRunable());
    }
}