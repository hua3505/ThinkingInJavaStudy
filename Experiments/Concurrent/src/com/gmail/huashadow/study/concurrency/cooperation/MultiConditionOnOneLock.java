package com.gmail.huashadow.study.concurrency.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wolf on 2017/4/17.
 * 研究从同一个锁生成的多个 Condition
 * 结果：同一个 Lock 生成的多个 Condition 对象，互不干扰。
 */
public class MultiConditionOnOneLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            lock.lock();
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("condition1 wake");
        });
        executorService.execute(() -> {
            lock.lock();
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("condition2 wake");
        });

        try {
            Thread.sleep(1000);
            lock.lock();
            condition1.signalAll();
            lock.unlock();
            Thread.sleep(1000);
            lock.lock();
            condition2.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        executorService.shutdown();
    }
}
