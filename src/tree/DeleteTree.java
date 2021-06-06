package problems.deletetree;

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
	}
}

public class DeleteTree {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.right.left = new Node(5);

		deletetheTree(root);
	}

	private static void deletetheTree(Node root) {
		if (null == root)
			return;

		deletetheTree(root.left);
		deletetheTree(root.right);
		root = null;
	}

}
