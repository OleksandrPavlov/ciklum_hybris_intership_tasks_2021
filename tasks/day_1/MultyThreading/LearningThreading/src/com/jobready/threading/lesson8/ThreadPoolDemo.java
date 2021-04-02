package com.jobready.threading.lesson8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable processor = new MessageProcessor(1);
        executorService.execute(processor);

        Runnable processor1 = new MessageProcessor(2);
        executorService.execute(processor1);

        Runnable processor2 = new MessageProcessor(3);
        executorService.execute(processor2);

        Runnable processor3 = new MessageProcessor(3);
        executorService.execute(processor3);

        executorService.shutdown();

        try {
            executorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The executor service was closed");

    }
}
