import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class Transaction {
    private String transactionId;
    private String policyNumber;
    private double amount;
    private Date transactionDate;
    private boolean isFraudulent;

    public Transaction(String transactionId, String policyNumber, double amount, Date transactionDate, boolean isFraudulent) {
        this.transactionId = transactionId;
        this.policyNumber = policyNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.isFraudulent = isFraudulent;
    }

    public String getTransactionId() { return transactionId; }
    public String getPolicyNumber() { return policyNumber; }
    public double getAmount() { return amount; }
    public Date getTransactionDate() { return transactionDate; }
    public boolean isFraudulent() { return isFraudulent; }
}

class FraudSummary {
    private String policyNumber;
    private long fraudCount;
    private double totalFraudAmount;

    public FraudSummary(String policyNumber, long fraudCount, double totalFraudAmount) {
        this.policyNumber = policyNumber;
        this.fraudCount = fraudCount;
        this.totalFraudAmount = totalFraudAmount;
    }

    public String getPolicyNumber() { return policyNumber; }
    public long getFraudCount() { return fraudCount; }
    public double getTotalFraudAmount() { return totalFraudAmount; }

    @Override
    public String toString() {
        return policyNumber + ": Fraud Count = " + fraudCount + ", Total Fraud = $" + String.format("%.2f", totalFraudAmount);
    }
}

public class FraudDetector {
    public static List<FraudSummary> detectFraud(List<Transaction> transactions) {
        Map<String, List<Transaction>> grouped = transactions.stream()
            .filter(t -> t.isFraudulent())
            .filter(t -> t.getAmount() > 10000)
            .collect(groupingBy(Transaction::getPolicyNumber));

        List<FraudSummary> alerts = grouped.entrySet().stream()
            .map(entry -> {
                String policy = entry.getKey();
                List<Transaction> txns = entry.getValue();
                long count = txns.size();
                double totalAmount = txns.stream().mapToDouble(Transaction::getAmount).sum();
                return new FraudSummary(policy, count, totalAmount);
            })
            .filter(s -> s.getFraudCount() > 5 || s.getTotalFraudAmount() > 50000)
            .collect(toList());

        return alerts;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("T1", "P1001", 15000, new Date(), true),
            new Transaction("T2", "P1001", 20000, new Date(), true),
            new Transaction("T3", "P1001", 18000, new Date(), true),
            new Transaction("T4", "P1001", 22000, new Date(), true),
            new Transaction("T5", "P1001", 25000, new Date(), true),
            new Transaction("T6", "P1001", 30000, new Date(), true),
            new Transaction("T7", "P1002", 12000, new Date(), true),
            new Transaction("T8", "P1002", 15000, new Date(), true),
            new Transaction("T9", "P1003", 9000, new Date(), true),
            new Transaction("T10", "P1003", 11000, new Date(), false)
        );

        List<FraudSummary> alerts = detectFraud(transactions);
        alerts.forEach(System.out::println);
    }
}
