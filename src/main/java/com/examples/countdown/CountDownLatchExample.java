package com.examples.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author Volodymyr Kvashenko
 * Created on 02.12.2017.
 */
class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Worker(countDownLatch);
        new Worker(countDownLatch);
        new Worker(countDownLatch);

        System.out.println("I'm '" + Thread.currentThread().getName() + "'. I'm waiting for the Workers done.");
        countDownLatch.await();

        System.out.println("All work done.");
    }

    static class Worker extends Thread {
        CountDownLatch countDownLatch;

        Worker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println("I'm " + getName() + ". I'm starting to work.");
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " the work done.");
            countDownLatch.countDown();
        }
    }
}
