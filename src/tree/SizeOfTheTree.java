package problems.size;

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
	}
}

public class SizeOfTheTree {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.right.left = new Node(5);

		System.out.println(sizeOftheTree(root, 0));
	}

	private static int sizeOftheTree(Node root, int size) {
		if (null == root)
			return 0;
		return sizeOftheTree(root.left, size) + 1 + sizeOftheTree(root.right, size);

	}

}
