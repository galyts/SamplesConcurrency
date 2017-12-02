package com.examples.phasers;

import java.util.concurrent.Phaser;

/**
 * @author Volodymyr Kvashenko
 * Created on 02.12.2017.
 */
class CarWash {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(2);

        new Washer(phaser);
        new Washer(phaser);
    }

    static class Washer extends Thread {
        final Phaser phaser;

        Washer(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(getName() + " washing the car.");
                phaser.arriveAndAwaitAdvance();
            }
        }
    }
}
