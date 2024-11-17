import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String amazonData = "src/amazon-product-data.csv";
        RedBlackTree tree = new RedBlackTree();
        boolean isFirstLine = true;

        // Read and insert products from the CSV file instead of StdIn
        try (BufferedReader br = new BufferedReader(new FileReader(amazonData))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                Product product = parseProduct(line);
                if (product != null) {
                    tree.insert(product);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            return;
        }

        // Prompt for user input to search for product IDs
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID's seperated by ',' or type 'exit' to quit: ");

        // Read user input and split it into individual IDs
        String userInput = scanner.nextLine().trim();
        if (!userInput.equalsIgnoreCase("exit")) {
            String[] searchIds = userInput.split(",");
            System.out.println("\nSearch Results:\n");

            for (String searchId : searchIds) {
                searchId = searchId.trim(); // Trim any extra whitespace
                Product result = tree.search(searchId);
                if (result != null) {
                    System.out.println("ID: " + searchId + "\n" + result);
                } else {
                    System.out.println("Product with ID '" + searchId + "' not found.");
                }
            }
        }

        // Insertions (testing purposes)
        System.out.println("\nInsertions:");
        Product newProduct = new Product("dummyID", "Wacky Gizmo", "Cool | Awesome | Radical", "12345.67");
        Product duplicateProduct = new Product("2bb94aefc3467ed83860e0e2712d5f10", "Duplicate Product", "Duplicate Category", "49.99");

        tree.insert(newProduct);
        System.out.println("\nInserted new product:\n" + newProduct);
        tree.insert(duplicateProduct); // Should trigger duplicate error

        scanner.close();
    }

    public static Product parseProduct(String line) {
        String uniqId = null;
        String name = null;
        String category = null;
        String price = "0.0";
        StringBuilder field = new StringBuilder();
        int fieldIndex = 0;
        boolean insideQuote = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                insideQuote = !insideQuote;
            } else if (c == ',' && !insideQuote) {
                if (fieldIndex == 0) uniqId = field.toString().trim();
                else if (fieldIndex == 1) name = field.toString().trim();
                else if (fieldIndex == 2) category = field.toString().trim();

                fieldIndex++;
                field.setLength(0);
            } else {
                field.append(c);
            }
        }

        if (fieldIndex == 3 || (fieldIndex == 2 && !field.toString().isEmpty())) {
            String priceString = field.toString().trim();
            price = priceString;
            int dashIndex = priceString.indexOf("-");
        }

        if (uniqId == null || name == null) return null;
        if (category == null) category = "";
        return new Product(uniqId, name, category, price);
    }
}
