package problems.insertelement;

import java.util.LinkedList;
import java.util.Queue;

class Node {
	int data;
	Node left;
	Node right;

	public Node(int value) {
		this.data = value;
	}
}

public class InsertAnElement {
	public static void main(String[] args) {
		Node root = new Node(5);
		root.left = new Node(9);
		root.right = new Node(4);
		root.left.right = new Node(3);
		root.right.left = new Node(8);

		insertAnElement(root, 10);
		insertAnElementWithoutRecursion(root, 90);
		printTree(root);
	}

	private static void insertAnElementWithoutRecursion(Node root, int i) {
		if (root == null)
			return;

		Queue<Node> queue = new LinkedList<Node>();
		Node node = root;
		queue.add(node);

		while (!queue.isEmpty()) {
			node = queue.remove();

			if (node.left != null) {
				queue.add(node.left);

			} else {
				node.left = new Node(i);
				return;
			}
			if (node.right != null) {
				queue.add(node.right);

			} else {
				node.right = new Node(i);
				return;
			}
		}

	}

	private static void printTree(Node root) {
		if (root == null)
			return;

		System.out.println(root.data);
		printTree(root.left);
		printTree(root.right);

	}

	private static void insertAnElement(Node root, int i) {
		if (root == null)
			return;
		else {
			if (root.left != null) {
				insertAnElement(root.left, i);
			} else {
				root.left = new Node(i);
				return;
			}
		}
	}
}
