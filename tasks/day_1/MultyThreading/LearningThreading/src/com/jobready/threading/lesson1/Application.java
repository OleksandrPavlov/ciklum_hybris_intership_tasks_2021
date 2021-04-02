package com.jobready.threading.lesson1;

public class Application {
    public static void main(String[] args) {
        Task taskRunner = new Task();
        taskRunner.start();
        System.out.println("Hello there!");

        Task taskRunner1 = new Task();
        taskRunner1.start();
    }
}

class Task extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1880; i++) {
            System.out.println("number: " + i);
        }
    }
}
