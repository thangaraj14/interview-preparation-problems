package tree;

public class BinaryTreeLCA {

    static Node root;

    public static void main(String[] args) {
        root = new Node(3);

        root.left = new Node(6);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(11);
        root.left.right.left = new Node(9);
        root.left.right.right = new Node(5);

        root.right.right = new Node(30);
        root.right.right.left = new Node(25);
        root.right.right.right = new Node(60);
        root.right.right.right.right = new Node(78);
        root.right.right.left.left = new Node(28);

        System.out.println(findLCA(root, 9, 5).getData());

    }

    private static Node findLCA(Node root, int i, int j) {
        if (null == root) {
            return null;
        }
        if (root.getData() == i || root.getData() == j) {
            return root;
        }

        Node left = findLCA(root.left, i, j);
        Node right = findLCA(root.right, i, j);

        if (null != left && null != right) {
            return root;
        }
        return left != null ? left : right;
    }
}
