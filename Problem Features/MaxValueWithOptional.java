import java.util.*;
import java.util.stream.*;

public class MaxValueWithOptional {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 25, 5, 40, 15);

        Optional<Integer> max = numbers.stream().max(Integer::compareTo);

        max.ifPresentOrElse(
            val -> System.out.println("Maximum value: " + val),
            () -> System.out.println("The list is empty. No maximum value.")
        );
    }
}
