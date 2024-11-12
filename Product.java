public class Product {
    public String uniqId;
    public String name;
    public String category;
    public double price;

    public Product(String uniqId, String name, String category, double price) {
        this.uniqId = uniqId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n    Category: " + category + "\n    Price: $" + price;
    }

}
