package com.jobready.threading.lesson5;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        InventoryManager inventoryManager = new InventoryManager();
        Thread inventoryTask = new Thread(() -> {
            inventoryManager.populateSoldProducts();
        });
        Thread displayTask = new Thread(() -> {
            inventoryManager.displaySoldProducts();
        });

        inventoryTask.start();
        Thread.sleep(2000);
        displayTask.start();
    }
}
