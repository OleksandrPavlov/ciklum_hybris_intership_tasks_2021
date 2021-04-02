package lesson4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) throws IOException {
        //Integer enumeration in range
        IntStream.range(1, 10).forEach(System.out::print);
        System.out.println("------------------------------");
        //Integer enumeration in range and skipping 5 elements
        IntStream.range(1, 10).skip(5).forEach(System.out::print);
        //Sorting strings
        Stream.of("Hello", "Bottle", "Africa").sorted().findFirst().ifPresent(System.out::println);
        //Filtering
        Stream.of(new String[]{"Hello", "Bottle", "Africa"}).filter(x -> x.contains("t")).sorted().findFirst().ifPresent(System.out::println);
        //Stream from file
        System.out.println("--------------");
        Files.lines(Paths.get("src/lesson4/stockDataCsv.txt")).
                filter((x) -> x.length() > 6).sorted().
                forEach(System.out::println);

        //Stream from file
        System.out.println("--------------");
        List<String> lines = Files.lines(Paths.get("src/lesson4/wordFile.txt")).
                filter((x) -> x.contains("th")).sorted().collect(Collectors.toList());
        System.out.println(lines);
    }
}
