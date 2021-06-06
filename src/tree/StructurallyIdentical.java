package tree;

class Node {

    int data;
    Node left;
    Node right;
    int height;
	Node next;
	Node prev;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}

public class StructurallyIdentical {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);

        Node duplicate = new Node(1);
        duplicate.left = new Node(2);
        duplicate.right = new Node(3);
        duplicate.left.right = new Node(4);
        duplicate.right.left = new Node(5);
        duplicate.right.right = new Node(6);

        System.out.println(isStructuallyIdentical(root, duplicate));
    }

    private static boolean isStructuallyIdentical(Node root, Node duplicate) {

	    if (root == null && duplicate == null) {
		    return true;
	    }

        if (root != null && duplicate != null) {
            return (root.data == duplicate.data && isStructuallyIdentical(root.left, duplicate.left)
                    && isStructuallyIdentical(root.right, duplicate.right));
        }
        return false;
    }
}
