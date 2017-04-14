package com.gmail.huashadow.study.concurrency.sharedresources;

/**
 * Created by wolf on 2017/4/11.
 * 偶数生成器
 */
public class EvenGenerator extends IntGenerator {

    private int mCurrentEvenValue = 0;

    @Override
    public int next() {
        ++mCurrentEvenValue;
        Thread.yield();
        ++mCurrentEvenValue;
        return mCurrentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
