package problems.numberofleaves;

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

public class NumberOfLeaves {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.right.left = new Node(5);
		root.right.right = new Node(7);
		System.out.println(numberOfLeaves(root));
		System.out.println(numberOfLeaveRecursion(root, 0));
	}

	private static int numberOfLeaveRecursion(Node root, int count) {
		if (null == root)
			return 0;

		if (null != root.left)
			count = numberOfLeaveRecursion(root.left, count);

		if (null != root.right)
			count = numberOfLeaveRecursion(root.right, count);

		if (null == root.right && null == root.left)
			count++;

		return count;
	}

	private static int numberOfLeaves(Node root) {

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

			if (null == node.right && null == node.left)
				count++;

		}

		return count;
	}

}
