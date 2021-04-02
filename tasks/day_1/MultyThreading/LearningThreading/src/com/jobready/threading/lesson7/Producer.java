package com.jobready.threading.lesson7;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    BlockingQueue<Integer> questionQueue;

    public Producer(BlockingQueue<Integer> questionQueue) {
        this.questionQueue = questionQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i < 15; i++) {
            try {
                try {
                    questionQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The question " + i + " has been read!");
            } catch (IllegalStateException ex) {
                System.out.println("The product list is full!");
            }
        }
    }
}
