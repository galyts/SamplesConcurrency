package com.examples.semofors;

import java.util.concurrent.Semaphore;

/**
 * @author Volodymyr Kvashenko
 * Created on 02.12.2017.
 */
class Restaurant {

    public static void main(String[] args) {
        Semaphore table = new Semaphore(2);

        Person person0 = new Person(table);
        Person person1 = new Person(table);
        Person person2 = new Person(table);
        Person person3 = new Person(table);
        Person person4 = new Person(table);
        Person person5 = new Person(table);
        Person person6 = new Person(table);
        Person person7 = new Person(table);
        Person person8 = new Person(table);
        Person person9 = new Person(table);

        person0.start();
        person1.start();
        person2.start();
        person3.start();
        person4.start();
        person5.start();
        person6.start();
        person7.start();
        person8.start();
        person9.start();
    }

    static class Person extends Thread {
        Semaphore table;

        Person(Semaphore table) {
            this.table = table;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " waiting for a table.");

            try {
                table.acquire();
                System.out.println(this.getName() + " eat at the table.");
                sleep(1000);
                System.out.println(this.getName() + " release the table.");
                table.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
