package com.examples.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author Volodymyr Kvashenko
 * Created on 02.12.2017.
 */
class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Mike(exchanger);
        new Questionnaire(exchanger);
    }

    static class Mike extends Thread {
        Exchanger<String> exchanger;

        Mike(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                exchanger.exchange("Hi! My name is Mike.");
                sleep(3000);
                exchanger.exchange("I'm 30 year old.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Questionnaire extends Thread {
        Exchanger<String> exchanger;

        Questionnaire(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange(null));
                System.out.println(exchanger.exchange(null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
