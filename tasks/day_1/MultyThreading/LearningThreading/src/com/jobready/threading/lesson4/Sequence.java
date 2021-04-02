package com.jobready.threading.lesson4;

public class Sequence {
    int value;

    public synchronized int getNext() {
        return value++;
    }
}
