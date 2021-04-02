package com.jobready.threading.lesson5;

public class Product {
    String name;
    int id;

    public Product(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "id " + id + "|" + " name " + name;
    }
}
