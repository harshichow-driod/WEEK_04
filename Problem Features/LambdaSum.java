@FunctionalInterface
interface SumFunction {
    int add(int a, int b);
}

public class LambdaSum {
    public static void main(String[] args) {
        SumFunction sum = (a, b) -> a + b;
        int result = sum.add(10, 20);
        System.out.println("Sum: " + result);
    }
}
