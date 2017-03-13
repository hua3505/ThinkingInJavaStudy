package com.gmail.huashadow.study.concurrent;

/**
 * Created by wolf on 2017/3/13.
 * 发射倒计时
 */
public class LiftOff implements Runnable {
    private static int sTaskCount = 0;
    private final int mId = sTaskCount++;
    private int mCountDown = 10;

    LiftOff() {
    }

    @SuppressWarnings("unused")
    public LiftOff(int countDown) {
        mCountDown = countDown;
    }

    private String status() {
        return "#" + mId + "(" +
                ((mCountDown > 0) ? mCountDown : "Liftoff!") + "), ";
    }

    @Override
    public void run() {
        while (mCountDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }
}
