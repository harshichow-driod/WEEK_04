import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class Claim {
    private String claimId;
    private String policyNumber;
    private double claimAmount;
    private Date claimDate;
    private String status;

    public Claim(String claimId, String policyNumber, double claimAmount, Date claimDate, String status) {
        this.claimId = claimId;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.status = status;
    }

    public String getClaimId() { return claimId; }
    public String getPolicyNumber() { return policyNumber; }
    public double getClaimAmount() { return claimAmount; }
    public Date getClaimDate() { return claimDate; }
    public String getStatus() { return status; }
}

class PolicySummary {
    private String policyNumber;
    private double totalAmount;
    private double averageAmount;

    public PolicySummary(String policyNumber, double totalAmount, double averageAmount) {
        this.policyNumber = policyNumber;
        this.totalAmount = totalAmount;
        this.averageAmount = averageAmount;
    }

    public String getPolicyNumber() { return policyNumber; }
    public double getTotalAmount() { return totalAmount; }
    public double getAverageAmount() { return averageAmount; }

    @Override
    public String toString() {
        return policyNumber + ": Total = $" + String.format("%.2f", totalAmount)
                + ", Average = $" + String.format("%.2f", averageAmount);
    }
}

public class ClaimsAnalyzer {
    public static List<PolicySummary> analyzeClaims(List<Claim> claims) {
        Map<String, List<Claim>> grouped = claims.stream()
            .filter(c -> c.getStatus().equalsIgnoreCase("Approved"))
            .filter(c -> c.getClaimAmount() > 5000)
            .collect(groupingBy(Claim::getPolicyNumber));

        List<PolicySummary> summaries = grouped.entrySet().stream()
            .map(entry -> {
                String policy = entry.getKey();
                List<Claim> claimList = entry.getValue();
                double total = claimList.stream().mapToDouble(Claim::getClaimAmount).sum();
                double avg = claimList.stream().mapToDouble(Claim::getClaimAmount).average().orElse(0);
                return new PolicySummary(policy, total, avg);
            })
            .sorted(Comparator.comparingDouble(PolicySummary::getTotalAmount).reversed())
            .limit(3)
            .collect(toList());

        return summaries;
    }

    public static void main(String[] args) {
        List<Claim> claims = Arrays.asList(
            new Claim("C1", "P1001", 7000, new Date(), "Approved"),
            new Claim("C2", "P1002", 3000, new Date(), "Pending"),
            new Claim("C3", "P1001", 8000, new Date(), "Approved"),
            new Claim("C4", "P1003", 10000, new Date(), "Approved"),
            new Claim("C5", "P1004", 2000, new Date(), "Approved"),
            new Claim("C6", "P1003", 6000, new Date(), "Approved"),
            new Claim("C7", "P1002", 9000, new Date(), "Approved"),
            new Claim("C8", "P1001", 4000, new Date(), "Approved")
        );

        List<PolicySummary> topPolicies = analyzeClaims(claims);
        topPolicies.forEach(System.out::println);
    }
}
