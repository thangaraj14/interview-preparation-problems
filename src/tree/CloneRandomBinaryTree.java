package tree;

import java.util.HashMap;
import java.util.Map;

// Data structure to store a special binary tree node
// with a random pointer

class CloneRandomBinaryTree {
    
    static class Node {
        int data;
        Node left, right;
        Node random;

        // Constructor
        Node(int data) {
            this.data = data;
        }
    }

    // Function to print the preorder traversal on a given binary tree
    public static void preorder(Node root) {
        if (root == null) {
            return;
        }

        // print the current node's data
        System.out.print(root.data + " —> (");

        // print left child's data
        System.out.print((root.left != null ? root.left.data : "X") + ", ");

        // print the right child's data
        System.out.print((root.right != null ? root.right.data : "X") + ", ");

        // print the random child's data
        System.out.println((root.random != null ? root.random.data : "X") + ")");

        // recur for the left and right subtree
        preorder(root.left);
        preorder(root.right);
    }

    // Recursive function to copy random pointers from the original binary tree
    // into the cloned binary tree using the map
    public static void updateRandomPointers(Node root, Map<Node, Node> map) {
        // base case
        if (map.get(root) == null) {
            return;
        }

        // update the random pointer of the cloned node
        map.get(root).random = map.get(root.random);

        // recur for the left and right subtree
        updateRandomPointers(root.left, map);
        updateRandomPointers(root.right, map);
    }

    // Recursive function to clone the data, left, and right pointers for
    // each node of a binary tree into a given map
    public static Node cloneLeftRightPointers(Node root, Map<Node, Node> map) {
        // base case
        if (root == null) {
            return null;
        }

        // clone all fields of the root node except the random pointer

        // create a new node with the same data as the root node
        map.put(root, new Node(root.data));

        // clone the left and right subtree
        map.get(root).left = cloneLeftRightPointers(root.left, map);
        map.get(root).right = cloneLeftRightPointers(root.right, map);

        // return cloned root node
        return map.get(root);
    }

    // The main function to clone a special binary tree with random pointers
    public static Node cloneSpecialBinaryTree(Node root) {
        // create a map to store mappings from a node to its clone
        Map<Node, Node> map = new HashMap<>();

        // clone data, left, and right pointers for each node of the original
        // binary tree, and put references into the map
        cloneLeftRightPointers(root, map);

        // update random pointers from the original binary tree in the map
        updateRandomPointers(root, map);

        // return the cloned root node
        return map.get(root);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.random = root.right.left.random;
        root.left.left.random = root.right;
        root.left.right.random = root;
        root.right.left.random = root.left.left;
        root.random = root.left;

        System.out.println("Preorder traversal of the original tree:");
        preorder(root);

        Node clone = cloneSpecialBinaryTree(root);

        System.out.println("\nPreorder traversal of the cloned tree:");
        preorder(clone);
    }
}