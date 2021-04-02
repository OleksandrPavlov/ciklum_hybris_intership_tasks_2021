package com.jobready.threading.lesson2;

public class Application {
    public static void main(String[] args) {
        System.out.println("Starting thread 1");
        Task taskRunner = new Task("Thread A");
        new Thread(taskRunner).start();

        System.out.println("Starting thread 2");
        Task taskRunner1 = new Task("Thread B");
        new Thread(taskRunner1).start();
    }
}

class Task implements Runnable {
    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        for (int i = 0; i < 1880; i++) {
            System.out.println("number: " + i + " - in " + Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
