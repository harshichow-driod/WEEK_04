import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FilterStringsWithPredicate {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "strawberry", "kiwi", "pineapple", "orange");

        Predicate<String> lengthGreaterThanFive = s -> s.length() > 5;
        Predicate<String> containsAn = s -> s.contains("an");

        List<String> filtered = words.stream()
            .filter(lengthGreaterThanFive.and(containsAn))
            .collect(Collectors.toList());

        filtered.forEach(System.out::println);
    }
}
