import java.util.*;
import java.util.stream.*;

public class FilterStrings {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Apple", "Banana", "Avocado", "Cherry", "Apricot", "Blueberry");

        List<String> filtered = words.stream()
            .filter(s -> !s.startsWith("A"))
            .collect(Collectors.toList());

        filtered.forEach(System.out::println);
    }
}
