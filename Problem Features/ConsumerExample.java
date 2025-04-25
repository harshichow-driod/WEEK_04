import java.util.*;
import java.util.function.*;

public class ConsumerExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

        words.forEach(printUpperCase);
    }
}
