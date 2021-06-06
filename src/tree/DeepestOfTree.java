package problems.deepest;

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

public class DeepestOfTree {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.right.left = new Node(5);

		System.out.println(deepestNode(root));
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
}