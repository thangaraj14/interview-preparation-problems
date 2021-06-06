package problems.fullNode;

import java.util.LinkedList;
import java.util.Queue;

class Node {

	Node left;
	Node right;
	int data;

	public Node(int value) {
		this.data = value;
	}

}

public class FullNodes {

	public static void main(String[] args) {

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.right.left = new Node(5);
		root.right.right = new Node(7);

		System.out.println(findFullNodes(root));
		System.out.println(findHalfNode(root));

	}

	private static int findHalfNode(Node root) {
		if (null == root)
			return 0;

		Queue<Node> queue = new LinkedList<Node>();
		Node node = root;
		queue.add(node);
		int count = 0;

		while (!queue.isEmpty()) {
			node = queue.remove();

			if (null != node.left)
				queue.add(node.left);

			if (null != node.right)
				queue.add(node.right);

			if ((null != node.right && null == node.left) || (null == node.right && null != node.left))
				count++;
		}

		return count;
	}

	private static int findFullNodes(Node root) {
		if (null == root)
			return 0;

		Queue<Node> queue = new LinkedList<Node>();
		Node node = root;
		queue.add(node);
		int count = 0;

		while (!queue.isEmpty()) {
			node = queue.remove();

			if (null != node.left)
				queue.add(node.left);

			if (null != node.right)
				queue.add(node.right);

			if (null != node.right && null != node.left)
				count++;
		}

		return count;
	}

}
