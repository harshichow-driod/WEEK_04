import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class Sale {
    private String productId;
    private int quantity;
    private double price;

    public Sale(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

class ProductSales {
    private String productId;
    private double totalRevenue;

    public ProductSales(String productId, double totalRevenue) {
        this.productId = productId;
        this.totalRevenue = totalRevenue;
    }

    public String getProductId() { return productId; }
    public double getTotalRevenue() { return totalRevenue; }

    @Override
    public String toString() {
        return productId + ": $" + String.format("%.2f", totalRevenue);
    }
}

public class SalesProcessor {
    public static List<ProductSales> processSales(List<Sale> sales) {
        List<ProductSales> productSalesList = sales.stream()
            .filter(s -> s.getQuantity() > 10)
            .map(s -> new ProductSales(s.getProductId(), s.getQuantity() * s.getPrice()))
            .sorted(Comparator.comparingDouble(ProductSales::getTotalRevenue).reversed())
            .limit(5)
            .collect(toList());

        return productSalesList;
    }

    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
            new Sale("P1", 15, 20.0),
            new Sale("P2", 8, 50.0),
            new Sale("P3", 12, 30.0),
            new Sale("P4", 25, 10.0),
            new Sale("P5", 5, 100.0),
            new Sale("P6", 20, 40.0),
            new Sale("P7", 11, 60.0)
        );

        List<ProductSales> topProducts = processSales(sales);
        topProducts.forEach(System.out::println);
    }
}
