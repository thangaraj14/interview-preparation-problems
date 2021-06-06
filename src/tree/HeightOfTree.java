package problems.heightoftree;

import java.util.LinkedList;
import java.util.Queue;

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
	}
}

public class HeightOfTree {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.right = new Node(6);
		root.right.left = new Node(7);
		root.left.left.left = new Node(8);
		root.left.left.right = new Node(9);
		root.left.left.right.left = new Node(19);

		 System.out.println(findHeightRecursion(root));

		System.out.println(findHeightWithoutRecursion(root));

		// System.out.println(deepestNode(root));
		// System.out.println(treeHeight(root));
		
	}

	private static int deepestNode(Node root) {
		if (null == root)
			return 0;
		int value = 0;

		Queue<Node> queue = new LinkedList<Node>();
		Node node = root;
		queue.add(node);

		while (!queue.isEmpty()) {

			node = queue.remove();
			value = node.data;

			if (null != node.left)
				queue.add(node.left);
			if (null != node.right)
				queue.add(node.right);
		}
		return value;
	}

	private static int findHeightWithoutRecursion(Node root) {
		if (root == null)
			return 0;

		Queue<Node> queue = new LinkedList<Node>();
		Node node = root;
		queue.add(node);
		int count = 1;

		while (!queue.isEmpty()) {

			node = queue.remove();
			if (queue.isEmpty())
				count++;

			if (null != node.left)
				queue.add(node.left);
			if (null != node.right)
				queue.add(node.right);
		}

		return count;
	}

	private static int findHeightRecursion(Node root) {
		if (root == null) {
			return 0;
		}

		int lheight = findHeightRecursion(root.left);
		int rheight = findHeightRecursion(root.right);

		if (lheight > rheight)
			return lheight + 1;
		return rheight + 1;

	}
}