package com.jobready.threading.lesson6;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Integer> questionList = new ArrayList<>();
        Producer producer = new Producer(questionList);
        Consumer consumer = new Consumer(questionList);

        Thread producerTask = new Thread(producer);
        Thread consumerTask = new Thread(consumer);

        producerTask.start();
        consumerTask.start();

    }
}
