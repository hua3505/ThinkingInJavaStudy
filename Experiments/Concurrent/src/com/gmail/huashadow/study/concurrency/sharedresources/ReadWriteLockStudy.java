package com.gmail.huashadow.study.concurrency.sharedresources;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wolf on 2017/4/12.
 * 研究读写锁
 */
public class ReadWriteLockStudy {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Thread thread1 = new Thread(() -> {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " get read lock");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " release read lock");
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " get write lock");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " release write lock");
        }, "thread2");
        Thread thread3 = new Thread(() -> {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " get read lock");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " release read lock");
        }, "thread3");
        try {
            thread1.start();
            Thread.sleep(100);
            thread2.start();
            Thread.sleep(100);
            thread3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
