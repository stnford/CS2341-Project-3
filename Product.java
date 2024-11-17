public class Product {
    public String uniqId;
    public String name;
    public String category;
    public String price;

    public Product(String uniqId, String name, String category, String price) {
        this.uniqId = uniqId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + uniqId + "\n    Name: " + name + "\n    Category: " + category + "\n    Price: " + price;
    }

}
