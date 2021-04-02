package lesson2;

public class Application {
    public static void main(String[] args) {
        Human human = new Human();
        Robot robot = new Robot();
        walk(human);
        walk(robot);

        ALambdaClass lambdaClass = () -> System.out.println("Hello from lambda!");
        lambdaClass.action();

        walk(()-> System.out.println("lambda as an argument"));
    }

    public static final void walk(Walkable entity) {
        entity.walk();
    }
}
