package dsa;

public class LCABST {

    static Node root;

    public static void main(String[] args) {

        root = new Node(10);

        root.left = new Node(-10);
        root.right = new Node(30);
        root.left.right = new Node(8);
        root.left.right.left = new Node(6);
        root.left.right.right = new Node(9);

        root.right.right = new Node(30);
        root.right.right.left = new Node(25);
        root.right.right.right = new Node(60);
        root.right.right.right.right = new Node(78);
        root.right.right.left.left = new Node(28);

        findLCA(28, 78);
        findLCA(6, 9);
        findLCA(6, 7);
        recursiveLCA(28, 78);
        recursiveLCA(6, 9);
        recursiveLCA(6, 7);
    }

    private static void findLCA(int i, int j) {
        if (null == root) {
            return;
        }
        Node temp = root;
        // it wont throw NPE because we will get result before it reaches null portion
        while (null != temp.left || null != temp.right) {
            if ((i < temp.data && j > temp.data) || (i == temp.data || j == temp.data)) {
                System.out.println("Found the LCA :" + temp.data);
                return;
            } else if (i < temp.data && j < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
    }

    private static void recursiveLCA(int i, int j) {
        if (null == root) {
            return;
        } else {
            Node temp = root;
            System.out.println(findRecursiveLCA(temp, i, j));
        }

    }

    private static long findRecursiveLCA(Node temp, int i, int j) {

        if (i < temp.getData() && j < temp.getData()) {
            return findRecursiveLCA(temp.left, i, j);
        } else if (i > temp.getData() && j > temp.getData()) {
            return findRecursiveLCA(temp.right, i, j);
        } else if ((i < temp.getData() && j > temp.getData()) || (i > temp.getData() && j < temp.getData())) {
            return temp.getData();
        }
        return 0;
    }

}
