package lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> animals = new ArrayList<>();
        animals.add("Lion");
        animals.add("Cat");
        animals.add("Dog");
        animals.add("Bird");

        /*for (int i = 0; i < animals.size(); i++) {
            System.out.println(animals.get(i));
        }
        for (String animal : animals) {
            System.out.println(animal);
        }
*/
        List<Vehicle> vehicles = new LinkedList<>();
        Vehicle vehicle = new Vehicle("Honda", "accord", 12000, false);
        vehicles.add(vehicle);
        vehicles.add(new Vehicle("Toyota", "camry", 25000, true));

        for (Vehicle value : vehicles) {
            System.out.println(value);
        }

        printElements(vehicles);
        printElements(animals);
    }

    public static void printElements(List elements) {
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i));
        }
    }
}
