package lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Application {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("Honda", "Accord", "Red", 23000),
                new Car("Toyota", "Accord", "Red", 18000),
                new Car("Nissan", "Accord", "Red", 31000),
                new Car("Mitsubishi", "Accord", "Red", 16000),
                new Car("Jeep", "Accord", "Red", 20000));
        printCarByCondition(cars, (car) -> car.getPrice() >= 18000 && car.getPrice() <= 20000);
        printCarByCondition(cars, (car) -> car.getColor().equals("Red"));

        Function<Car, String> carToStringConverter = Car::getModel;
        String stringCar = carToStringConverter.apply(cars.get(0));
        System.out.println(stringCar);
    }

    public static void printCarByCondition(List<Car> cars, Predicate<Car> condition) {
        for (Car car : cars) {
            if (condition.test(car)) {
                System.out.println(car);
            }
        }
    }


}

@FunctionalInterface
interface CarCondition {
    boolean test(Car car);
}