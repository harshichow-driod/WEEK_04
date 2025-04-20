import java.util.*;

abstract class WarehouseItem {
    String name;
    public WarehouseItem(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract String getCategory();
}

class Electronics extends WarehouseItem {
    public Electronics(String name) {
        super(name);
    }
    public String getCategory() {
        return "Electronics";
    }
}

class Groceries extends WarehouseItem {
    public Groceries(String name) {
        super(name);
    }
    public String getCategory() {
        return "Groceries";
    }
}

class Furniture extends WarehouseItem {
    public Furniture(String name) {
        super(name);
    }
    public String getCategory() {
        return "Furniture";
    }
}

class Storage<T extends WarehouseItem> {
    private List<T> items = new ArrayList<>();
    public void addItem(T item) {
        items.add(item);
    }
    public List<T> getItems() {
        return items;
    }
}

public class Program1 {
    public static void displayItems(List<? extends WarehouseItem> items) {
        for (WarehouseItem item : items) {
            System.out.println(item.getCategory() + ": " + item.getName());
        }
    }

    public static void main(String[] args) {
        Storage<Electronics> electronicsStorage = new Storage<>();
        electronicsStorage.addItem(new Electronics("Laptop"));
        electronicsStorage.addItem(new Electronics("Smartphone"));

        Storage<Groceries> groceriesStorage = new Storage<>();
        groceriesStorage.addItem(new Groceries("Apples"));
        groceriesStorage.addItem(new Groceries("Rice"));

        Storage<Furniture> furnitureStorage = new Storage<>();
        furnitureStorage.addItem(new Furniture("Chair"));
        furnitureStorage.addItem(new Furniture("Table"));

        displayItems(electronicsStorage.getItems());
        displayItems(groceriesStorage.getItems());
        displayItems(furnitureStorage.getItems());
    }
}
