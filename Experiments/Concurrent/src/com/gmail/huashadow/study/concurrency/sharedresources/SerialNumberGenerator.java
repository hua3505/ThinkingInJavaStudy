package com.gmail.huashadow.study.concurrency.sharedresources;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wolf on 2017/4/15.
 * 序列数字生成器
 */
public class SerialNumberGenerator {
    private static volatile int sSerialNumber = 0;
    private static volatile AtomicInteger sAtomicSerialNumber = new AtomicInteger(0);
    public static int nextSerialNumber() {
        return sSerialNumber++; // 不是线程安全的
    }
    public static int nextSerialNumberAtomicly() {
        return sAtomicSerialNumber.incrementAndGet(); // 线程安全的
    }
}
