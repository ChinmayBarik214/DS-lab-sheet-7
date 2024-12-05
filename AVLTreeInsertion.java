import java.util.*;

class AVLTreeInsertion {
  class Node {
    String key;
    int height;
    Node left, right;

    Node(String key) {
      this.key = key;
      this.height = 1;
    }
  }

  private Node root;

  // Utility to get the height of the tree
  int height(Node node) {
    return node == null ? 0 : node.height;
  }

  // Utility to get the balance factor of a node
  int getBalance(Node node) {
    return node == null ? 0 : height(node.left) - height(node.right);
  }

  // Right rotate the subtree rooted at y
  Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;

    return x;
  }

  // Left rotate the subtree rooted at x
  Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    x.height = Math.max(height(x.left), height(x.right)) + 1;
    y.height = Math.max(height(y.left), height(y.right)) + 1;

    return y;
  }

  // Insert a key into the AVL Tree
  Node insert(Node node, String key) {
    if (node == null)
      return new Node(key);

    if (key.compareTo(node.key) < 0)
      node.left = insert(node.left, key);
    else if (key.compareTo(node.key) > 0)
      node.right = insert(node.right, key);
    else
      return node; // Duplicates not allowed

    node.height = 1 + Math.max(height(node.left), height(node.right));

    int balance = getBalance(node);

    // Perform rotations
    if (balance > 1 && key.compareTo(node.left.key) < 0) // LL Case
      return rightRotate(node);

    if (balance < -1 && key.compareTo(node.right.key) > 0) // RR Case
      return leftRotate(node);

    if (balance > 1 && key.compareTo(node.left.key) > 0) { // LR Case
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    if (balance < -1 && key.compareTo(node.right.key) < 0) { // RL Case
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }

  // Insert method
  void insert(String key) {
    root = insert(root, key);
  }

  // In-order traversal to print the tree
  void inOrder(Node node, List<String> result) {
    if (node != null) {
      inOrder(node.left, result);
      result.add(node.key);
      inOrder(node.right, result);
    }
  }

  List<String> getInOrder() {
    List<String> result = new ArrayList<>();
    inOrder(root, result);
    return result;
  }

  public static void main(String[] args) {
    AVLTreeInsertion tree = new AVLTreeInsertion();
    tree.insert("Region1");
    tree.insert("Region2");
    tree.insert("Region3");

    System.out.println("In-order: " + tree.getInOrder()); // Output: ["Region1", "Region2", "Region3"]
  }
}
