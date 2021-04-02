package com.jobready.threading.lesson3;

public class Sequence {
    int value;

    public synchronized int getNext() {
        return value++;
    }
}
