package geeksforgeeks;

<<<<<<< HEAD
//https://www.geeksforgeeks.org/remove-bst-keys-outside-the-given-range/
=======
/**
 * https://www.geeksforgeeks.org/remove-bst-keys-outside-the-given-range/
 */
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
class RemoveBSTGivenOutsideRange {

    private static BSTNode removeOutsideRange(BSTNode root, int min, int max) {
        if (root == null) {
            return null;
        }

        root.left = removeOutsideRange(root.left, min, max);
        root.right = removeOutsideRange(root.right, min, max);

        if (root.data < min) {
            BSTNode rchild = root.right;
            root = null;
            return rchild;
        }

        if (root.data > max) {
            BSTNode lchild = root.left;
            root = null;
            return lchild;
        }
        return root;
    }

    public static BSTNode newNode(int num) {
        BSTNode temp = new BSTNode();
        temp.data = num;
        temp.left = null;
        temp.right = null;
        return temp;
    }

    public static BSTNode insert(BSTNode root, int data) {
        if (root == null) {
            return newNode(data);
        }
        if (root.data > data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    private static void inorderTraversal(BSTNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        BSTNode root = null;
        root = insert(root, 6);
        root = insert(root, -13);
        root = insert(root, 14);
        root = insert(root, -8);
        root = insert(root, 15);
        root = insert(root, 13);
        root = insert(root, 7);

        System.out.print("Inorder Traversal of " + "the given tree is: ");
        inorderTraversal(root);

        root = removeOutsideRange(root, -10, 13);

        System.out.print("\nInorder traversal of " + "the modified tree: ");
        inorderTraversal(root);
    }
}

class BSTNode {
    int data;
    BSTNode left;
    BSTNode right;
}
