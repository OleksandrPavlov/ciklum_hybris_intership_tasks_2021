package com.jobready.threading.lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventoryManager {
    List<Product> soldProducts = new CopyOnWriteArrayList<>();
    List<Product> purchasedProducts = new ArrayList<>();

    public void populateSoldProducts() {
        for (int i = 0; i < 1000; i++) {
            Product product = new Product("test_product" + i, i);
            soldProducts.add(product);
            System.out.println("Added: " + product);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void displaySoldProducts() {

        for (Product product : soldProducts) {
            System.out.println("SOLD: " + product);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
