import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class PolicyHolder {
    private String holderId;
    private String name;
    private int age;
    private String policyType;
    private double premiumAmount;

    public PolicyHolder(String holderId, String name, int age, String policyType, double premiumAmount) {
        this.holderId = holderId;
        this.name = name;
        this.age = age;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
    }

    public String getHolderId() { return holderId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPolicyType() { return policyType; }
    public double getPremiumAmount() { return premiumAmount; }
}

class RiskAssessment {
    private String holderId;
    private String name;
    private double riskScore;

    public RiskAssessment(String holderId, String name, double riskScore) {
        this.holderId = holderId;
        this.name = name;
        this.riskScore = riskScore;
    }

    public String getHolderId() { return holderId; }
    public String getName() { return name; }
    public double getRiskScore() { return riskScore; }

    @Override
    public String toString() {
        return holderId + " - " + name + ": Risk Score = " + String.format("%.2f", riskScore);
    }
}

public class RiskAssessor {
    public static Map<String, List<RiskAssessment>> assessRisk(List<PolicyHolder> holders) {
        List<RiskAssessment> assessments = holders.stream()
            .filter(h -> h.getPolicyType().equalsIgnoreCase("Life"))
            .filter(h -> h.getAge() > 60)
            .map(h -> new RiskAssessment(h.getHolderId(), h.getName(), h.getPremiumAmount() / h.getAge()))
            .sorted(Comparator.comparingDouble(RiskAssessment::getRiskScore).reversed())
            .collect(toList());

        Map<String, List<RiskAssessment>> categorized = assessments.stream()
            .collect(groupingBy(r -> r.getRiskScore() > 0.5 ? "High Risk" : "Low Risk"));

        return categorized;
    }

    public static void main(String[] args) {
        List<PolicyHolder> holders = Arrays.asList(
            new PolicyHolder("H1", "Alice", 65, "Life", 400),
            new PolicyHolder("H2", "Bob", 70, "Life", 500),
            new PolicyHolder("H3", "Charlie", 72, "Life", 300),
            new PolicyHolder("H4", "David", 55, "Life", 600),
            new PolicyHolder("H5", "Eve", 68, "Health", 450),
            new PolicyHolder("H6", "Frank", 75, "Life", 450),
            new PolicyHolder("H7", "Grace", 80, "Life", 600)
        );

        Map<String, List<RiskAssessment>> result = assessRisk(holders);
        result.forEach((category, list) -> {
            System.out.println(category + ":");
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}
