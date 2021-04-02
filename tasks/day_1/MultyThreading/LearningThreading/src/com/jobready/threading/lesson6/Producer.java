package com.jobready.threading.lesson6;

import java.util.List;

public class Producer implements Runnable {
    List<Integer> questionList;
    final int LIMIT = 5;

    public Producer(List<Integer> questionList) {
        this.questionList = questionList;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            try {
                readQuestion(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readQuestion(int questionNumber) throws InterruptedException {
        synchronized (questionList) {
        while (questionList.size() == LIMIT) {
            System.out.println("Questions has piled up...Wait for answer!");
                try {
                    questionList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            questionList.add(questionNumber);
            Thread.sleep(300);
            System.out.println("The question number " + questionNumber + " has been read!");
            questionList.notify();
        }
    }
}
