package geeksforgeeks;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
 * <p>
 * Iterative Java program to convert a Binary Tree to its mirror
 */
class MirrorBinaryTree {

    static class Node {
        int data;
        Node left;
        Node right;
    }

    static Node newNode(int data) {
        Node node = new Node();
        node.data = data;
        node.left = node.right = null;
        return (node);
    }

    static void mirror(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (q.size() > 0) {
<<<<<<< HEAD
            Node curr = q.poll();
=======

            Node curr = q.remove();

            // swap the elements
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if (curr.left != null) {
                q.add(curr.left);
            }
            if (curr.right != null) {
                q.add(curr.right);
            }
        }
    }

    static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void main(String args[]) {
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.left.right = newNode(5);

        System.out.print("\n Inorder traversal of the" + " coned tree is \n");
        inOrder(root);

        mirror(root);

        System.out.print("\n Inorder traversal of the " + "mirror tree is \n");
        inOrder(root);
    }
} 
