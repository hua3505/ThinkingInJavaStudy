package com.gmail.huashadow.study.concurrency.sharedresources;

/**
 * Created by wolf on 2017/4/14.
 * 研究 Volatie
 */
public class VolatileSutdy {

    static int sI;

    public static void main(String[] args) {
        int i = 1;
        sI = 0;
        i++;
        sI++;
    }
}
