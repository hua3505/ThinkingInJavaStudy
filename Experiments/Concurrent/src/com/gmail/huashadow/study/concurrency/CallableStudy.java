package com.gmail.huashadow.study.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wolf on 2017/3/24.
 * Callable研究
 */
public class CallableStudy {

    private static final int STUDENT_NUM_OF_EACH_CLASS = 50;

    static class ClassScoreCaculator implements Callable<Integer> {
        private List<Integer> loadScore() {
            List<Integer> scoreList = new ArrayList<>();
            for (int i = 0; i < STUDENT_NUM_OF_EACH_CLASS; ++i) {
                scoreList.add((int) (Math.random() * 100));
            }
            return scoreList;
        }

        @Override
        public Integer call() throws Exception {
            List<Integer> scoreList = loadScore();
            Integer sum = 0;
            for (Integer score : scoreList) {
                sum += score;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        List<Future<Integer>> results = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 12; ++i) {
            results.add(executor.submit(new ClassScoreCaculator()));
        }
        int sumScore = 0;
        for (Future<Integer> result : results) {
            try {
                sumScore += result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        int average = sumScore / (STUDENT_NUM_OF_EACH_CLASS * 12);
        System.out.print("average score is " + average);
    }
}
