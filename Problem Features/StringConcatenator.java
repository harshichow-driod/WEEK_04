import java.util.function.BiFunction;

public class StringConcatenator {
    public static void main(String[] args) {
        BiFunction<String, String, String> concatenateWithSpace = (s1, s2) -> s1 + " " + s2;

        String first = "Hello";
        String second = "World";

        String result = concatenateWithSpace.apply(first, second);

        System.out.println("Concatenated String: " + result);
    }
}
