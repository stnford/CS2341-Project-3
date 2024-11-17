public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Product product;
        Node left, right;
        boolean color; // RED or BLACK

        Node(Product product, boolean color) {
            this.product = product;
            this.color = color;
        }
    }

    private Node root;

    // Helper method to check if a node is red
    private boolean isRed(Node node) {
        if (node == null){
            return false;
        }
        return node.color == RED;
    }

    // Inserts a new Product into the RB Tree and checks for duplicates
    public boolean insert(Product product) {
        if (search(product.uniqId) != null) {
            System.out.println("\nError: Product with ID " + product.uniqId + " already exists.");
            return false; // Duplicate found
        }
        root = insert(root, product);
        root.color = BLACK; // Root must always be black
        return true; // Successful insertion
    }

    private Node insert(Node node, Product product) {
        if (node == null){
            return new Node(product, RED);
        }

        int cmp = product.uniqId.compareTo(node.product.uniqId);
        if (cmp < 0){
            node.left = insert(node.left, product);
        }
        else if (cmp > 0){
            node.right = insert(node.right, product);
        }

        // Fix right-leaning red link
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        // Fix two consecutive left-leaning red links
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        // Split 4-node
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = RED;
        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = RED;
        return temp;
    }

    private void flipColors(Node node) {
        node.color = RED;
        if (node.left != null) node.left.color = BLACK;
        if (node.right != null) node.right.color = BLACK;
    }

    // Search for a Product by its uniqId
    public Product search(String uniqId) {
        Node node = root;
        while (node != null) {
            int cmp = uniqId.compareTo(node.product.uniqId);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.product;
        }
        return null; // Not found
    }
}