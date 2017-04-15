package com.gmail.huashadow.study.concurrency.sharedresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wolf on 2017/4/15.
 * 检验 SerialNumberGenerator 是不是线程安全
 * 检验结果：遇到最小的在600+就出现了重复，最大的在6000+。
 * 更有意思的是，居然出现了两个重复数字, 而且相差52：
 *  Duplicate: 3662
 *  Duplicate: 3714
 * 难道是第一个检测到重复的线程，执行 System.exit() 之前被切换了？
 *
 * 改用原子操作后是线程安全的
 */
public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet sSerials = new CircularSet(1000);
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
//                int serial = SerialNumberGenerator.nextSerialNumberAtomicly();
                if (sSerials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                sSerials.add(serial);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new SerialChecker());
        }
        if (args.length > 0) {
            try {
                TimeUnit.SECONDS.sleep(new Integer(args[0]));
                System.out.println("No duplicates detected");
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++) {
            if (array[i] == val) {
                return true;
            }
        }
        return false;
    }
}

