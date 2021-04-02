package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        HashSet<Employee> employees = new HashSet<>();
        employees.add(new Employee("Mike", 3500, "Accounting"));
        employees.add(new Employee("Poul", 3000, "Admin"));
        employees.add(new Employee("Peter", 4000, "IT"));
        employees.add(new Employee("Angela", 2000, "Maint"));
        ArrayList<Employee> myList = new ArrayList<>(employees);

        Collections.sort(myList);
        System.out.println(myList);
    }
}
