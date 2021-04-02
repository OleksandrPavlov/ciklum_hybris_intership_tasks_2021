package com.jobready.threading.lesson7;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> questionQueue;

    public Consumer(BlockingQueue<Integer> questionQueue) {
        this.questionQueue = questionQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int i = questionQueue.take();
                System.out.println("Answering question..." + i);
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
