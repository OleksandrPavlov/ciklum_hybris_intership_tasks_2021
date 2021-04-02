package com.jobready.threading.lesson7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Application {
    public static void main(String[] args) {
        BlockingQueue<Integer> questionQueue = new ArrayBlockingQueue<>(5);
        Consumer consumer = new Consumer(questionQueue);
        Producer producer = new Producer(questionQueue);

        Thread consumerTask = new Thread(consumer);
        Thread producerTask = new Thread(producer);

        consumerTask.start();
        producerTask.start();
    }
}
