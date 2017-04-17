package com.gmail.huashadow.study.concurrency.sharedresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wolf on 2017/4/15.
 * 研究 ThreadLocal
 */
public class ThreadLocalStudy {

    // 注意了！！！
    // 按理说，用了 ThreadLocal，每个线程加完之后sVal得值应该都是2。可是发现会有3、4、5，甚至7
    // 打印了一下线程名称，发现是线程被复用了。因为我这里的每个任务执行时间都非常短。
    // 所以使用ThreadLocal的时候还是得注意线程被复用的情况，这可能导致错误的状态。


    private static ThreadLocal<Integer> sVal = ThreadLocal.withInitial(() -> (1));

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                sVal.set(sVal.get() + 1);
                System.out.println(Thread.currentThread().getName() + " " + sVal.get());
            });
        }
        executorService.shutdown();
    }
}
