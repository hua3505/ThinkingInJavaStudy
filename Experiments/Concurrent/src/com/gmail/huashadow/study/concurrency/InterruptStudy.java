package com.gmail.huashadow.study.concurrency;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wolf on 2017/4/17.
 * 研究线程的终止
 * interrupt 可以打断 sleep wait 等的阻塞。但无法打断io、synchronized 引起的阻塞
 * 对于 io ，可以通过关闭底层资源等方式结束阻塞。
 * shutdownNow 似乎也只是去 interrupt 线程，并不能结束io和synchronized阻塞的线程。
 */
public class InterruptStudy {
    static class IOBlocked implements Runnable {
        private InputStream mInputStream;

        IOBlocked(InputStream inputStream) {
            mInputStream = inputStream;
        }

        @Override
        public void run() {
            try {
                mInputStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("IOBlocked exit");
        }
    }

    static class SynchronizedBlocked implements Runnable {

        public synchronized void f() {
            while (true) {
                Thread.yield();
            }
        }

        public SynchronizedBlocked() {
            new Thread() {
                @Override
                public void run() {
                    f();
                }
            }.start();
        }

        @Override
        public void run() {
            System.out.println("Trying to call f()");
            f();
            System.out.println("Exiting SynchronizedBlocked.run()");

        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future futureIOBlocked = executorService.submit(new IOBlocked(System.in));
        Future futureSynchronizedBlocked = executorService.submit(new SynchronizedBlocked());
        Future futureSleepBlocked = executorService.submit(() -> {
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("SleepBlocked exit");
        });

        futureIOBlocked.cancel(true);
        futureSynchronizedBlocked.cancel(true);
        futureSleepBlocked.cancel(true);

//        executorService.shutdownNow();


        executorService.shutdown();

        try {
            System.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
