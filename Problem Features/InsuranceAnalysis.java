import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class InsurancePolicy {
    String policyNumber;
    String holderName;
    double premiumAmount;

    public InsurancePolicy(String policyNumber, String holderName, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.premiumAmount = premiumAmount;
    }

    public String toString() {
        return policyNumber + " | " + holderName + " | $" + premiumAmount;
    }
}

public class InsuranceAnalysis {
    public static void main(String[] args) {
        List<InsurancePolicy> policies = Arrays.asList(
            new InsurancePolicy("P001", "Alice", 1500),
            new InsurancePolicy("P002", "Bob", 900),
            new InsurancePolicy("P003", "Charlie", 2000),
            new InsurancePolicy("P004", "David", 2500),
            new InsurancePolicy("P005", "Eve", 1100),
            new InsurancePolicy("P006", "Frank", 3000),
            new InsurancePolicy("P007", "Alice", 1800)
        );

        List<InsurancePolicy> filtered = policies.stream()
            .filter(p -> p.premiumAmount > 1200)
            .collect(Collectors.toList());

        List<InsurancePolicy> sortedByName = policies.stream()
            .sorted(Comparator.comparing(p -> p.holderName))
            .collect(Collectors.toList());

        double totalPremium = policies.stream()
            .mapToDouble(p -> p.premiumAmount)
            .sum();

        policies.forEach(p -> System.out.println(p));

        List<InsurancePolicy> rangeFiltered = policies.stream()
            .filter(p -> p.premiumAmount >= 1000 && p.premiumAmount <= 2000)
            .collect(Collectors.toList());

        InsurancePolicy maxPremiumPolicy = policies.stream()
            .max(Comparator.comparingDouble(p -> p.premiumAmount))
            .orElse(null);

        Map<Character, List<InsurancePolicy>> groupedByInitial = policies.stream()
            .collect(Collectors.groupingBy(p -> p.holderName.charAt(0)));

        double averagePremium = policies.stream()
            .mapToDouble(p -> p.premiumAmount)
            .average()
            .orElse(0);

        List<InsurancePolicy> sortedByPremium = policies.stream()
            .sorted(Comparator.comparingDouble(p -> p.premiumAmount))
            .collect(Collectors.toList());

        boolean anyAbove2000 = policies.stream()
            .anyMatch(p -> p.premiumAmount > 2000);

        Map<String, Long> premiumRanges = policies.stream()
            .collect(Collectors.groupingBy(p -> {
                if (p.premiumAmount <= 1000) return "0-1000";
                else if (p.premiumAmount <= 2000) return "1001-2000";
                else return ">2000";
            }, Collectors.counting()));

        Set<String> uniqueHolders = policies.stream()
            .map(p -> p.holderName)
            .collect(Collectors.toSet());

        List<InsurancePolicy> nameContains = policies.stream()
            .filter(p -> p.holderName.contains("Alice"))
            .collect(Collectors.toList());

        Map<String, Double> policyToPremium = policies.stream()
            .collect(Collectors.toMap(p -> p.policyNumber, p -> p.premiumAmount));

        System.out.println("Filtered: " + filtered);
        System.out.println("Sorted by Name: " + sortedByName);
        System.out.println("Total Premium: $" + totalPremium);
        System.out.println("Range Filtered: " + rangeFiltered);
        System.out.println("Max Premium Policy: " + maxPremiumPolicy);
        System.out.println("Grouped by Initial: " + groupedByInitial);
        System.out.println("Average Premium: $" + averagePremium);
        System.out.println("Sorted by Premium: " + sortedByPremium);
        System.out.println("Any Above $2000: " + anyAbove2000);
        System.out.println("Premium Ranges Count: " + premiumRanges);
        System.out.println("Unique Holders: " + uniqueHolders);
        System.out.println("Name Contains 'Alice': " + nameContains);
        System.out.println("Policy to Premium Map: " + policyToPremium);
    }
}

class WordFrequency {
    public static void main(String[] args) {
        String text = "This is a sample text with some sample words and this text is a simple example.";

        List<String> words = Arrays.asList(text.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+"));

        Map<String, Long> frequency = words.stream()
            .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> topN = frequency.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toList());

        topN.forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}

class SecondMostRepeatedWord {
    public static void main(String[] args) {
        String text = "apple banana apple orange banana apple orange banana orange";

        List<String> words = Arrays.asList(text.toLowerCase().split("\\s+"));

        Map<String, Long> frequency = words.stream()
            .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> sorted = frequency.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .collect(Collectors.toList());

        if (sorted.size() >= 2) {
            Map.Entry<String, Long> secondMost = sorted.get(1);
            System.out.println("Second most repeated word: " + secondMost.getKey() + " (" + secondMost.getValue() + ")");
        } else {
            System.out.println("Not enough unique words.");
        }
    }
}
