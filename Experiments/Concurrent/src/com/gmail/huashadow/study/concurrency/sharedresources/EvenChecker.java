package com.gmail.huashadow.study.concurrency.sharedresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wolf on 2017/4/11.
 * 偶数检查器
 */
public class EvenChecker implements Runnable {
    private IntGenerator mGenerator;
    private final int mId;

    public EvenChecker(IntGenerator generator, int ident) {
        mGenerator = generator;
        mId = ident;
    }

    public void run() {
        while (!mGenerator.isCanceled()) {
            int val = mGenerator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even");
                mGenerator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < count; ++i) {
            executor.execute(new EvenChecker(generator, i));
        }
        executor.shutdown();
    }

    public static void test(IntGenerator generator) {
        test(generator, 10);
    }
}
