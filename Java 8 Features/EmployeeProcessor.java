import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return String.format("%d - %s (%s): $%.2f", id, name, department, salary);
    }
}

public class EmployeeProcessor {
    public static void processEmployees(List<Employee> employees) {
        List<Employee> filtered = employees.stream()
            .filter(e -> e.getDepartment().equalsIgnoreCase("Engineering"))
            .filter(e -> e.getSalary() > 80000)
            .collect(toList());

        List<Employee> sorted = filtered.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            .collect(toList());

        Map<String, List<Employee>> grouped = sorted.stream()
            .collect(groupingBy(Employee::getDepartment));

        Map<String, Double> averageSalaries = grouped.entrySet().stream()
            .collect(toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .collect(averagingDouble(Employee::getSalary))
            ));

        grouped.forEach((dept, empList) -> {
            System.out.println("Department: " + dept);
            empList.forEach(System.out::println);
            System.out.println("Average Salary: $" + String.format("%.2f", averageSalaries.get(dept)));
            System.out.println();
        });
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", "Engineering", 90000),
            new Employee(2, "Bob", "Engineering", 85000),
            new Employee(3, "Charlie", "HR", 70000),
            new Employee(4, "David", "Engineering", 95000),
            new Employee(5, "Eve", "Sales", 80000),
            new Employee(6, "Frank", "Engineering", 75000)
        );

        processEmployees(employees);
    }
}

