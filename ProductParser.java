public class ProductParser {
    public static Product parseProduct(String line) {
        String uniqId, name, category;
        double price = 0.0;

        int firstComma = line.indexOf(",");
        uniqId = line.substring(0, firstComma);

        int nameStart = firstComma + 1;
        int nameEnd;
        if (line.charAt(nameStart) == '"') {
            // Product name is quoted; find the closing quote
            nameEnd = line.indexOf('"', nameStart + 1) + 1;
            name = line.substring(nameStart + 1, nameEnd - 1); // Extract without quotes
            // Skip the comma following the closing quote
            nameEnd++;
        } else {
            // Product name is not quoted; find next comma
            nameEnd = line.indexOf(",", nameStart);
            name = line.substring(nameStart, nameEnd);
        }

        // Find category and price
        int categoryStart = nameEnd + 1;
        int priceStart = line.lastIndexOf(",");
        if (priceStart > categoryStart) {
            category = line.substring(categoryStart, priceStart).trim();
            String priceString = line.substring(priceStart + 1).replace("$", "").trim();

            // If price is a range like "$74.99 - $249.99", take the lower value
            int dashIndex = priceString.indexOf("-");
            if (dashIndex != -1) {
                priceString = priceString.substring(0, dashIndex).trim();
            }

            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price for product: " + uniqId);
                price = 0.0;
            }
        } else {
            category = "";
            price = 0.0;
        }

        return new Product(uniqId, name, category, price);
    }
}
