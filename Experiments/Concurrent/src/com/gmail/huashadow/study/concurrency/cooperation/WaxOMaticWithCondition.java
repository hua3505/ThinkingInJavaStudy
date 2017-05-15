package com.gmail.huashadow.study.concurrency.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wolf on 2017/4/17.
 * 给汽车上蜡的例子。使用 Lock 和 Condition 重写。
 */
public class WaxOMaticWithCondition {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new WaxOn(car));
        executorService.execute(new WaxOff(car));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }

    static class Car {
        private boolean waxOn = false;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        void waxed() {
            lock.lock();
            try {
                waxOn = true;
                condition.signalAll();
            } finally {
                // 养成好习惯，把 unlock 放到 finally 中，避免异常导致锁无法释放
                lock.unlock();
            }
        }

        void buffed() {
            lock.lock();
            try {
                waxOn = false;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        void waitForWaxing() throws InterruptedException {
            lock.lock();
            try {
                while (!waxOn) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }

        void waitForBuffing() throws InterruptedException {
            lock.lock();
            try {
                while (waxOn) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    static class WaxOn implements Runnable {
        private Car car;

        WaxOn(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("Wax On! " + System.currentTimeMillis());
                    Thread.sleep(200);
                    car.waxed();
                    car.waitForBuffing();

                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On task");
        }
    }

    static class WaxOff implements Runnable {
        private Car car;

        WaxOff(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    car.waitForWaxing();
                    System.out.println("Wax Off! " + System.currentTimeMillis());
                    Thread.sleep(200);
                    car.buffed();

                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax Off task");
        }
    }
}

