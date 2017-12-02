package com.examples.forkjoinpool;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Volodymyr Kvashenko
 * Created on 02.12.2017.
 */
class FJPExample {

    static long numOfOperations = 10_000_000_000L;
    static int numOfThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        System.out.println(new Date());
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        System.out.println(pool.invoke(new Worker(0, numOfOperations)));
        System.out.println(new Date());
    }

    static class Worker extends RecursiveTask<Long> {
        long from, to;

        Worker(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if ((to - from) <= numOfOperations / numOfThreads) {
                long j = 0;

                for (long i = from; i < to; i++) {
                    j += i;
                }

                return j;
            } else {
                long middle = (to + from) / 2;

                Worker firstHalf = new Worker(from, middle);
                firstHalf.fork();

                Worker secondHalf = new Worker(middle + 1, to);

                return firstHalf.join() + secondHalf.compute();
            }
        }
    }
}
