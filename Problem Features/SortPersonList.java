import java.util.*;
import java.util.stream.*;

class Person {
    private String name;
    private int age;
    private double salary;

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Salary: $" + salary + ")";
    }
}

public class SortPersonList {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, 50000),
            new Person("Bob", 25, 55000),
            new Person("Charlie", 35, 60000),
            new Person("Diana", 28, 52000)
        );

        List<Person> sortedByAge = people.stream()
            .sorted(Comparator.comparingInt(Person::getAge))
            .collect(Collectors.toList());

        sortedByAge.forEach(System.out::println);
    }
}
