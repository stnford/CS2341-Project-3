class Node {
    public Product product;
    public Node left, right, parent;
    public boolean isRed;

    public Node(Product product) {
        this.product = product;
        this.isRed = true;  // New nodes start red
    }
}