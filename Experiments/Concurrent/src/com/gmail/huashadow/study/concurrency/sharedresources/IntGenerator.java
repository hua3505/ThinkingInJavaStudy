package com.gmail.huashadow.study.concurrency.sharedresources;

/**
 * Created by wolf on 2017/4/11.
 * int 类型数字生成器抽象类
 */
public abstract class IntGenerator {
    private volatile boolean mCanceled = false;
    public abstract int next();

    public void cancel() {
        mCanceled = true;
    }

    public boolean isCanceled() {
        return mCanceled;
    }
}
