package com.examples.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Volodymyr Kvashenko
 * Created on 02.12.2017.
 */
class OlympicGames {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Race());

        new Athlete(cyclicBarrier);
        new Athlete(cyclicBarrier);
        new Athlete(cyclicBarrier);
    }

    static class Race extends Thread {
        @Override
        public void run() {
            System.out.println("The race is begin.");
        }
    }

    static class Athlete extends Thread {
        CyclicBarrier barrier;

        Athlete(CyclicBarrier barrier) {
            this.barrier = barrier;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println("I'm " + getName() + ". I'm ready to run.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
