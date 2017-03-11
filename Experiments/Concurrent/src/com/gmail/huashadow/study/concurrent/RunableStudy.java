package com.gmail.huashadow.study.concurrent;

/**
 * Created by wolf on 2017/3/11.
 * Runable研究
 */
public class RunableStudy {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        runnable.run();
    }
}
