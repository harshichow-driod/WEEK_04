import java.util.*;

interface Category {
    String getCategoryName();
}

class BookCategory implements Category {
    public String getCategoryName() {
        return "Book";
    }
}

class ClothingCategory implements Category {
    public String getCategoryName() {
        return "Clothing";
    }
}

class GadgetCategory implements Category {
    public String getCategoryName() {
        return "Gadget";
    }
}

class Product<T extends Category> {
    private String name;
    private double price;
    private T category;

    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public T getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class ProductCatalog {
    private List<Product<? extends Category>> products = new ArrayList<>();

    public void addProduct(Product<? extends Category> product) {
        products.add(product);
    }

    public List<Product<? extends Category>> getProducts() {
        return products;
    }

    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double discountedPrice = product.getPrice() * (1 - percentage / 100);
        product.setPrice(discountedPrice);
    }
}

public class Program2 {
    public static void main(String[] args) {
        Product<BookCategory> book = new Product<>("Java Basics", 499.0, new BookCategory());
        Product<ClothingCategory> shirt = new Product<>("Cotton Shirt", 799.0, new ClothingCategory());
        Product<GadgetCategory> phone = new Product<>("Smartphone", 14999.0, new GadgetCategory());

        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(book);
        catalog.addProduct(shirt);
        catalog.addProduct(phone);

        ProductCatalog.applyDiscount(book, 10);
        ProductCatalog.applyDiscount(shirt, 15);
        ProductCatalog.applyDiscount(phone, 5);

        for (Product<? extends Category> p : catalog.getProducts()) {
            System.out.println(p.getCategory().getCategoryName() + " - " + p.getName() + ": ₹" + p.getPrice());
        }
    }
}

