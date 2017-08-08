package com.gmail.huashadow.study.concurrency.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by wolf on 2017/6/25.
 * 对象池，使用信号量
 */
public class Pool<T> {
    private int mSize;
    private List<T> mItems = new ArrayList<T>();
    private volatile boolean[] mCheckedOut;
    private Semaphore mAvaliable;

    public Pool(Class<T> classObject, int size) {
        mSize = size;
        mCheckedOut = new boolean[size];
        mAvaliable = new Semaphore(size, true);
        for (int i = 0; i < size; ++i) {
            try {
                mItems.add(classObject.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public T checkout() throws InterruptedException {
        mAvaliable.acquire();
        return getItem();
    }

    public void checkIn(T item) {
        if (releaseItem(item)) {
            mAvaliable.release();
        }
    }

    private T getItem() {
        for (int i = 0; i < mSize; ++i) {
            if (!mCheckedOut[i]) {
                mCheckedOut[i] = true;
                return mItems.get(i);
            }
        }
        return null;
    }

    private boolean releaseItem(T item) {
        int index = mItems.indexOf(item);
        if (index != -1) {
            mCheckedOut[index] = false;
            return true;
        }
        return false;
    }


}

