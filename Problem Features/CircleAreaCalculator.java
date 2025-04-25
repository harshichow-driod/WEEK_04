import java.util.function.Function;

public class CircleAreaCalculator {
    public static void main(String[] args) {
        Function<Double, Double> calculateArea = r -> Math.PI * r * r;

        double radius = 5.0;
        double area = calculateArea.apply(radius);

        System.out.println("Area of circle with radius " + radius + " is: " + area);
    }
}
