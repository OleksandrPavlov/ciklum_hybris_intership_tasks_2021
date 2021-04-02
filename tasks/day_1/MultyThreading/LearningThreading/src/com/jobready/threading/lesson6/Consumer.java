package com.jobready.threading.lesson6;

import java.util.List;

public class Consumer implements Runnable {
    List<Integer> questionList;

    public Consumer(List<Integer> questionList) {
        this.questionList = questionList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                answerQuestion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void answerQuestion() throws InterruptedException {
        synchronized (questionList) {
        while (questionList.isEmpty()) {
            System.out.println("No one question has been read!");
                try {
                    questionList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Answering question..." + questionList.remove(0));
            questionList.notify();
        Thread.sleep(3000);

        }
    }
}
