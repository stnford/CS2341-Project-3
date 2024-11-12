public class Main {

    public static void main(String[] args) {
        int lineCount = 0;
        boolean isFirstLine = true;  // Skip the first line of the file

        // Process up to 100 lines from StdIn
        while (!StdIn.isEmpty() && lineCount < 1000) {
            String line = StdIn.readLine();

            // Skip the first line (header)
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            Product product = parseProduct(line);
            if (product != null) {
                System.out.println(product);
                lineCount++;
            }
        }
    }

    // parseProduct should be outside the main method
    public static Product parseProduct(String line) {
        String uniqId = null, name = null, category = null;
        double price = 0.0;
        StringBuilder field = new StringBuilder();
        int fieldIndex = 0;
        boolean insideQuote = false;

        // Traverse each character to split fields manually
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                insideQuote = !insideQuote; // Toggle insideQuote state
            } else if (c == ',' && !insideQuote) {
                // End of field when outside quotes
                if (fieldIndex == 0) {
                    uniqId = field.toString().trim();
                } else if (fieldIndex == 1) {
                    name = field.toString().trim();
                } else if (fieldIndex == 2) {
                    category = field.toString().trim();
                }
                fieldIndex++;
                field.setLength(0); // Clear field for next value
            } else {
                field.append(c);
            }
        }

        // Last field after loop
        if (fieldIndex == 3 || (fieldIndex == 2 && !field.toString().isEmpty())) {
            String priceString = field.toString().replace("$", "").trim();
            int dashIndex = priceString.indexOf("-");
            if (dashIndex != -1) {
                priceString = priceString.substring(0, dashIndex).trim(); // Take lower price in range
            }
            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format for product: " + uniqId);
                price = 0.0; // Default to 0.0 if unparseable
            }
        }

        // Assign missing fields if any
        if (uniqId == null || name == null) return null; // Invalid essential fields
        if (category == null) category = "";
        return new Product(uniqId, name, category, price);
    }
}
