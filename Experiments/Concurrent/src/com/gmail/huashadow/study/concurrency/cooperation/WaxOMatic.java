package com.gmail.huashadow.study.concurrency.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by wolf on 2017/4/17.
 * 给汽车上蜡的例子。研究 wait 和 notify 的使用
 */
public class WaxOMatic {

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

}

class Car {
    private boolean waxOn = false;

    synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            wait();
        }
    }

    synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
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

class WaxOff implements Runnable {
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
        System.out.println("Ending Wax On task");
    }
}
