package lesson1;

public class Application {
    public static void main(String[] args) {
        Human human = new Human();
        Robot robot = new Robot();
        walk(human);
        walk(robot);

        ALambdaClass lambdaClass = () -> System.out.println("Hello from lambda!");
        lambdaClass.action();

        walk(() -> System.out.println("lambda as an argument"));
        ALambdaClass hello = () ->
                System.out.println("Hello there");
        Calculator adder = (a, b) -> {
            return a + b;
        };
        Calculator divider = (a, b) -> b == 0 ? 0 : a / b;
        GenericInterface<String> reverser = (str) -> {
            StringBuilder stringBuilder = new StringBuilder(str);
            return stringBuilder.reverse().toString();
        };
        GenericInterface<Integer> processor = (number) -> number * 2;

        hello.action();
        System.out.println("Sum: " + adder.compute(4, 7));
        System.out.println("Divide: " + divider.compute(7, 2));
        System.out.println("String to reverse: reverse - " + reverser.operate("reverse"));
        System.out.println("One number processor: " + processor.operate(2));
    }

    public static final void walk(Walkable entity) {
        entity.walk();
    }

    public static void sayHello() {
        System.out.println("Hello!");
    }

    public int sum(int a, int b) {
        return a + b;
    }

}

@FunctionalInterface
interface Calculator {
    int compute(int a, int b);
}

@FunctionalInterface
interface GenericInterface<P> {
    P operate(P value);
}

