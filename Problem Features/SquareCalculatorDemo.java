@FunctionalInterface
interface SquareCalculator {
    int square(int x);

    default void printResult(int x) {
        System.out.println("The square of " + x + " is: " + square(x));
    }
}

public class SquareCalculatorDemo {
    public static void main(String[] args) {
        SquareCalculator calculator = n -> n * n;

        int number = 7;
        calculator.printResult(number);
    }
}
