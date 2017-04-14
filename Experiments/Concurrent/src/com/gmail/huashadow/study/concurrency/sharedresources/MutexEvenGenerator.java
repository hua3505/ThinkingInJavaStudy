package com.gmail.huashadow.study.concurrency.sharedresources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wolf on 2017/4/11.
 * 通过 Lock 来实现互斥的偶数生成器
 */
public class MutexEvenGenerator extends IntGenerator {

    private int mCurrentEvenValue = 0;
    private Lock mLock = new ReentrantLock();

    @Override
    public int next() {
        try {
            mLock.lock();
            ++mCurrentEvenValue;
            Thread.yield();
            ++mCurrentEvenValue;
            return mCurrentEvenValue;
        } finally {
            mLock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
