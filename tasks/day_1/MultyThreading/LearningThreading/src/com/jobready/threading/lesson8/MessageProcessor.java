package com.jobready.threading.lesson8;

public class MessageProcessor implements Runnable {
    private int message;

    public MessageProcessor(int message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " RECEIVED the message = " + message);
        respondToMessage();
        System.out.println(Thread.currentThread().getName()+ " RESPONSE HAS BEEN Submitted");
    }

    private void respondToMessage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Unable to process message!");
        }
    }
}
