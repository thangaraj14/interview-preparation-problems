package practiceproblems;

public class SortedArrayToBST {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Node node = bst(arr, 0, arr.length - 1);
        printTree(node);
    }

    public static Node bst(int[] arr, int start, int end) {
        if (end < start)
            return null;
        int mid = start + ((end - start) / 2);
        Node root = new Node(arr[mid]);
        root.left = bst(arr, start, mid - 1);
        root.right = bst(arr, mid + 1, end);
        return root;
    }

    public static void printTree(Node node) {
        if (node == null)
            return;
        printTree(node.left);
        System.out.println(node.data);
        printTree(node.right);

    }
}