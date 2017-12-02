package com.examples.threadfactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author Volodymyr Kvashenko
 * Created on 02.12.2017.
 */
class FactoryOfThreads {

    public static void main(String[] args) {
        final ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;
            }
        };

        threadFactory.newThread(new MyRunner()).start();
    }

    static class MyRunner implements Runnable {

        @Override
        public void run() {
            System.out.println("I am.");
        }
    }
}
